package com.exmp.mvvm.model

interface MenuService {

    class Data {
        var menuList: MutableList<Menu>? = null

        class Menu {
            var menuSeqNo: Int? = null       // id
            var menuTitle: String? = null       // 이름

            constructor(seqNo: Int?, title: String?) {
                this.menuSeqNo = seqNo
                this.menuTitle = title
            }

            override fun toString(): String {
                return "$menuSeqNo,$menuTitle"
            }
        }
    }
}
