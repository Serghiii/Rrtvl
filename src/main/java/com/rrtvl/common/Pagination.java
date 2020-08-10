package com.rrtvl.common;

public class Pagination {

    private String ref;
    private String code;
    private int size;
    private int page;
    private int step;

    public Pagination() {
    }

    public Pagination(int size, int page, int step, String ref) {
        this.ref = ref;
        this.code = "";
        this.size = size;
        this.page = page;
        this.step = step;
    }

    public String getCode() {
        PrevButton();
        if (step<3)Start1(); else Start2();
        NextButton();
        return code;
    }

    // add pages by number (from [s] to [f])
    private void Add(int s, int f) {
        for (int i = s; i < f; i++) {
            code += ("<li class=\"paginationjs-page J-paginationjs-page" + (i==page?" active":"") + "\">"+"<a" + (i!=page?(" href=\""+ref+"?page="+Integer.toString(i)+"\""):"") + ">" + Integer.toString(i) + "</a>" + "</li>");
        }
    }

    // add last page with separator
    private void Last() {
        code += ("<li class=\"paginationjs-ellipsis disabled\"><a>...</a></li>" + "<li class=\"paginationjs-page J-paginationjs-page" + (size==page?" active":"") + "\">"+"<a" + (size!=page?(" href=\""+ref+"?page="+Integer.toString(size)+"\""):"") + ">" + Integer.toString(size) + "</a>" + "</li>");
    }

    // add first page with separator
    private void First() {
        code += ("<li class=\"paginationjs-page J-paginationjs-page" + (page==1?" active":"") + "\">"+"<a" + (page!=1?(" href=\""+ref+"?page=1\""):"") + ">1</a><li class=\"paginationjs-ellipsis disabled\"><a>...</a></li>");
    }

    // previous button
    private void PrevButton() {
        code+= "<li title=\"Попередня сторінка\"" + (page==1?" class=\"disabled\"":"") + "><a" + (page!=1?(" href=\""+ref+"?page="+Integer.toString(page-1)+"\""):"") + ">&#9668;</a></li>";
    }

    // next button
    private void NextButton() {
        code+= "<li title=\"Наступна сторінка\"" + (page==size?" class=\"disabled\"":"") + "><a" + (page!=size?(" href=\""+ref+"?page="+Integer.toString(page+1)+"\""):"") + ">&#9658;</a></li>";
    }

    // start pagination
    private void Start1 () {
        if (size < step * 2 + 6) {
            Add(1, size + 1);
        }
        else if (page <= step * 2 + 1) {
            Add(1, step * 2 + 4);
            Last();
        }
        else if (page >= size - step * 2) {
            First();
            Add(size - step * 2 - 2, size + 1);
        }
        else {
            First();
            Add(page - step, page + step + 1);
            Last();
        }
    }

    // start pagination
    private void Start2 () {
        if (size < step * 2 + 6) {
            Add(1, size + 1);
        }
        else if (page < step * 2 + 1) {
            Add(1, step * 2 + 4);
            Last();
        }
        else if (page > size - step * 2) {
            First();
            Add(size - step * 2 - 2, size + 1);
        }
        else {
            First();
            Add(page - step, page + step + 1);
            Last();
        }
    }

}
