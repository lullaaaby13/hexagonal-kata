package com.lullaby.study.hexagonalkata.interfaces;

import java.util.List;

public class PagingResponse<T> {

    private final List<T> list;
    private final PagingModel paging;

    public PagingResponse(List<T> list, int page, int size) {
        this.list = list;
        this.paging = new PagingModel(page, size, 0);
    }

    public static class PagingModel {
        private final Integer page;
        private final Integer size;
        private final Integer total;

        public PagingModel(Integer page, Integer size, Integer total) {
            this.page = page;
            this.size = size;
            this.total = total;
        }

        public Integer getPage() {
            return page;
        }

        public Integer getSize() {
            return size;
        }

        public Integer getTotal() {
            return total;
        }
    }

    public List<T> getList() {
        return list;
    }

    public PagingModel getPaging() {
        return paging;
    }
}
