package com.wyx.toolbox.common.tools.util;

import com.wyx.toolbox.common.tools.entity.Menu;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *使用简单健壮的泛型+函数式接口来接收入参和出参实现属性数据的处理
 * 函数式接口解释：
 * Function<T, R>：接受一个参数，返回一个结果。
 * Consumer<T>：接受一个参数，没有返回值。
 * Supplier<T>：不接受参数，返回一个结果。
 * Predicate<T>：接受一个参数，返回一个boolean值。
 * UnaryOperator<T>：接受和返回同类型的对象。
 * BinaryOperator<T>：接受两个相同类型的参数并返回相同类型的结果。
 * @author wangyu
 * 参考文章：https://mp.weixin.qq.com/s/SdyRQUk1aOdf5Yq7ArRM7A
 */
public class TreeUtils {

    public static void main(String[] args) {
        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu(1L, -1L, "总", new ArrayList<>()));
        menuList.add(new Menu(2L, 1L, "一级1", new ArrayList<>()));
        menuList.add(new Menu(3L, 1L, "一级2", new ArrayList<>()));
        menuList.add(new Menu(4L, 1L, "一级3", new ArrayList<>()));
        menuList.add(new Menu(5L, 2L, "二级1", new ArrayList<>()));
        menuList.add(new Menu(6L, 3L, "三级", new ArrayList<>()));
        menuList.add(new Menu(7L, 5L, "四级", new ArrayList<>()));
        menuList.add(new Menu(8L, 6L, "五级", new ArrayList<>()));
        menuList.add(new Menu(9L, 8L, "六级", new ArrayList<>()));
        menuList.add(new Menu(10L, 2L, "二级", new ArrayList<>()));

        List<Menu> treeMenuList = makeTree(menuList, Menu::getParentId, Menu::getId, x -> x.getParentId() == -1L, Menu::setSubMenus);
        System.out.println("生成树形结构");
        System.out.println(treeMenuList);
        System.out.println("查找包含“三级”菜单的树");
        List<Menu> filterMenus = filter(menuList, x -> x.getName() != null && x.getName().contains("三级"), Menu::getSubMenus);
        System.out.println(filterMenus);

        System.out.println("排序");
        // 定义比较器（比如按 ID 排序）
        Comparator<Menu> nodeComparator = Comparator.comparingLong(Menu::getId);
        List<Menu> sort = sort(menuList, nodeComparator, Menu::getSubMenus);
        System.out.println(sort);



    }

    /**
     * 构建树
     *
     * @param treeList       需要合成树的List - 平铺结构
     * @param pIdGetter      对象中获取父级ID方法,如:Menu:getParentId
     * @param idGetter       对象中获取主键ID方法 ,如：Menu:getId
     * @param rootCheck      判断对象是否根节点的方法，如： x->x.getParentId()==null,x->x.getParentMenuId()==0
     * @param setSubChildren 对象中设置下级数据列表方法，如： Menu::setSubMenus
     * @return List<E>  树形结构
     */
    public static <T, E> List<E> makeTree(List<E> treeList, Function<E, T> pIdGetter, Function<E, T> idGetter, Predicate<E> rootCheck, BiConsumer<E, List<E>> setSubChildren) {
        Map<T, List<E>> parentMenuMap = new HashMap<>();
        for (E node : treeList) {
            //获取父节点id
            T parentId = pIdGetter.apply(node);
            //节点放入父节点的value内
            parentMenuMap.computeIfAbsent(parentId, k -> new ArrayList<>()).add(node);
        }
        List<E> result = new ArrayList<>();
        for (E node : treeList) {
            //添加到下级数据中
            setSubChildren.accept(node, parentMenuMap.getOrDefault(idGetter.apply(node), new ArrayList<>()));
            //如里是根节点，加入结构
            if (rootCheck.test(node)) {
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 树中查找（当前节点不匹配predicate，只要其子节点列表匹配到即保留）
     * @param tree  需要查找的树 - 平铺结构
     * @param predicate  过滤条件，根据业务场景自行修改
     * @param getSubChildren 获取下级数据方法，如：MenuVo::getSubMenus
     * @return List<E> 过滤后的树 -树形结构
     * @param <E> 泛型实体对象
     */
    public static <E> List<E> search(List<E> tree, Predicate<E> predicate, Function<E, List<E>> getSubChildren) {
        List<E> result = new ArrayList<>();

        for (E item : tree) {
            List<E> childList = getSubChildren.apply(item);
            List<E> filteredChildren = new ArrayList<>();

            if (childList != null && !childList.isEmpty()) {
                filteredChildren = search(childList, predicate, getSubChildren);
            }
            // 如果当前节点匹配，或者至少有一个子节点保留，就保留当前节点
            if (predicate.test(item) || !filteredChildren.isEmpty()) {
                result.add(item);
                // 还原下一级子节点如果有
                if (!filteredChildren.isEmpty()) {
                    getSubChildren.apply(item).clear();
                    getSubChildren.apply(item).addAll(filteredChildren);
                }
            }
        }
        return result;
    }

    /**
     * 树中过滤
     * 过滤满足条件的数据节点，如里当前节点不满足其所有子节点都会过滤掉。
     * @param tree  需要进行过滤的树 - 平铺结构
     * @param predicate  过滤条件判断
     * @param getChildren 获取下级数据方法，如：Menu::getSubMenus
     * @return List<E> 过滤后的树 - 树形结构
     * @param <E> 泛型实体对象
     */
    public static <E> List<E> filter(List<E> tree, Predicate<E> predicate, Function<E, List<E>> getChildren) {
        return tree.stream().filter(item -> {
            if (predicate.test(item)) {
                List<E> children = getChildren.apply(item);
                if (children != null && !children.isEmpty()) {
                    filter(children, predicate, getChildren);
                }
                return true;
            }
            return false;
        }).collect(Collectors.toList());
    }

    /**
     * 对树形结构进行排序
     *
     * @param tree         要排序的树形结构，表示为节点列表 - 平铺结构
     * @param comparator   用于节点比较的比较器。
     * @param getChildren  提供一种方法来获取每个节点的子节点列表。
     * @param <E>          元素的类型。
     * @return 排序后的节点列表。- 树形结构
     */
    public static <E> List<E> sort(List<E> tree, Comparator<? super E> comparator, Function<E, List<E>> getChildren) {
        // 对树的每个节点进行迭代处理
        for (E item : tree) {
            // 获取当前节点的子节点列表
            List<E> childList = getChildren.apply(item);
            // 如果子节点列表不为空，则递归调用 sort 方法对其进行排序
            if (childList != null && !childList.isEmpty()) {
                sort(childList, comparator, getChildren);
            }
        }
        // 对当前层级的节点列表进行排序
        // 这一步确保了所有直接子节点在递归返回后都已排序，然后对当前列表进行排序
        tree.sort(comparator);
        // 返回排序后的节点列表
        return tree;
    }

    /**
     * 树中过滤并进行节点处理（此处是将节点的choose字段置为false，那么就在页面上可以展示但无法被勾选）
     * 横向拓展，加入过滤或查找条件，即可完成一个通用树形过滤方法。
     * 再延伸，查找到匹配的节点后对此节点进行特殊业务需求处理也是使用频率极高的。
     * @param tree        需要过滤的树 - 平铺结构
     * @param predicate   过滤条件
     * @param getChildren 获取下级数据方法，如：Menu::getSubMenus
     * @param setChoose   要被处理的字段，如：Menu::getChoose，可根据业务场景自行修改
     * @param <E>         泛型实体对象
     * @return List<E> 过滤后的树 - 树形结构
     */
    public static <E> List<E> filterAndHandler(List<E> tree, Predicate<E> predicate, Function<E, List<E>> getChildren, BiConsumer<E, Boolean> setChoose) {
        return tree.stream().filter(item -> {
            //如果命中条件，则可以被勾选。（可根据业务场景自行修改）
            if (predicate.test(item)) {
                setChoose.accept(item, true);
            } else {
                setChoose.accept(item, false);
            }
            List<E> children = getChildren.apply(item);
            if (children != null && !children.isEmpty()) {
                filterAndHandler(children, predicate, getChildren, setChoose);
            }
            return true;
        }).collect(Collectors.toList());
    }
}
