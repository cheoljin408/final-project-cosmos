package org.kosta.finalproject.model.domain;

public class PagingBean {
    /**
     * 현재 페이지
     */
    private int nowPage = 1;
    /**
     * 페이지당 게시물수
     */
    private int postCountPerPage = 3;
    /**
     * 페이지 그룹당 페이지수
     */
    private int pageCountPerPageGroup = 5;
    /**
     * database에 저장된 총게시물수
     */
    private int totalPostCount;

    public PagingBean(int totalPostCount) {
        this.totalPostCount = totalPostCount;
    }

    public PagingBean(int totalPostCount, int nowPage) {
        this.totalPostCount = totalPostCount;
        this.nowPage = nowPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    /**
     * 현재 페이지번호에 해당하는 시작 게시물의 row number를 반환 <br>
     * hint : 이전페이지의 마지막 번호 + 1 <br>
     * 2페이지의 시작번호는 1페이지의 마지막 번호(5) + 1 이 시작번호이다<br>
     * ListController에서 총게시물수를 조회한 후 PagingBean 생성하여<br>
     * BoardDAO의 getPostingList 메서드의 매개변수에 전달 <br>
     * 아래 메서드를 이용해 조회할 게시물의 시작 row number를 할당하여 조회하게 한다
     */
    public int getStartRowNumber() {
        return (this.nowPage-1)*this.postCountPerPage + 1;
    }

    /**
     * 현재 페이지에서 보여줄 게시물 행(row)의 마지막 번호<br>
     * 현재 페이지*postCountPerPage(페이지 당 게시물수)의 연산결과가 게시물의 마지막 번호. 하지만 총게시물수보다<br>
     * 현재 페이지*postCountPerPage(페이지 당 게시물수)의 연산결과가 클 경우<br>
     * 총게시물수가 마지막 번호가 되어야 한다<br>
     * ex) 총게시물수 7 개 총페이지는 2페이지 : 1 2 3 4 5 | 6 7 | 1page 2page 현재페이지는 2페이지이고
     * 2*5(페이지당 게시물수) 는 10 이고 <br>
     * 실제 마지막 번호 7이다<br>
     * 연산결과가 총게시물수보다 클 경우 총게시물수가 마지막번호가 되어야 함
     *
     * @return
     */
    public int getEndRowNumber() {
        int endRowNumber = this.nowPage*this.postCountPerPage;

        if(endRowNumber > totalPostCount) {
            endRowNumber = totalPostCount;
        }
        return endRowNumber;
    }

    /**
     * 총 페이지 수를 return한다.<br>
     * 1. 전체 데이터(게시물) % 한 페이지에 보여줄 데이터 개수 <br>
     * => 0 이면 둘을 / 값이 총 페이지 수<br>
     * 2. 전체 데이터(게시물) % 한 페이지에 보여줄 데이터 개수 <br>
     * => 0보다 크면 둘을 / 값에 +1을 한 값이 총 페이지 수<br>
     * 게시물수 1 2 3 4 5 6 7 8 9 10 11 12<br>
     * 1페이지 1~5<br>
     * 2페이지 6~10<br>
     * 3페이지 11,12 <br>
     * ex) 게시물 32 개 , 페이지당 게시물수 5개-> 7 페이지
     *
     * @return
     */
    private int getTotalPage() {
        int totalPage = 0;

        if(this.totalPostCount % this.postCountPerPage == 0) {
            totalPage = this.totalPostCount / this.postCountPerPage;
        }
        else {
            totalPage = this.totalPostCount / this.postCountPerPage + 1;
        }

        return totalPage;
    }

    /**
     * 총 페이지 그룹의 수를 return한다.<br>
     * 1. 총 페이지수 % Page Group 당 Page 수. <br>
     * => 0 이면 둘을 / 값이 총 페이지 수<br>
     * 2. 총 페이지수 % Page Group 당 Page 수. <br>
     * => 0보다 크면 둘을 / 값에 +1을 한 값이 총 페이지 그룹수<br>
     * ex) 총 게시물 수 23 개 <br>
     * 총 페이지 ? 총 페이지 그룹수 ? <br>
     * 페이지 1 2 3 4 5<br>
     * 페이지그룹 1234(1그룹) 5(2그룹)<br>
     *
     */
    private int getTotalPageGroup() {
        int totalPageGroup = 0;

        if(this.getTotalPage() % this.pageCountPerPageGroup == 0) {
            totalPageGroup = this.getTotalPage()/this.pageCountPerPageGroup;
        }
        else {
            totalPageGroup = this.getTotalPage()/this.pageCountPerPageGroup + 1;
        }

        return totalPageGroup;
    }

    /**
     * 현재 페이지가 속한 페이지 그룹 번호(몇 번째 페이지 그룹인지) 을 return 하는 메소드 <br>
     * 1. 현재 페이지 % Page Group 당 Page 수 => 0 이면 <br>
     * 둘을 / 값이 현재 페이지 그룹. <br>
     * 2. 현재 페이지 % Page Group 당 Page 수 => 0 크면 <br>
     * 둘을 / 값에 +1을 한 값이 현재 페이지 그룹<br>
     * 페이지 1 2 3 4 /5 6 7 8/ 9 10 1그룹 2그룹 3그룹
     *
     * @return
     */
    private int getNowPageGroup() {
        int nowPageGroup = 0;

        if(this.nowPage % this.pageCountPerPageGroup == 0) {
            nowPageGroup = this.nowPage / this.pageCountPerPageGroup;
        }
        else {
            nowPageGroup = this.nowPage / this.pageCountPerPageGroup + 1;
        }

        return nowPageGroup;
    }

    /**
     * 현재 페이지가 속한 페이지 그룹의 시작 페이지 번호를 return 한다.<br>
     * Page Group 내 Page 수*(현재 페이지 그룹 -1) + 1을 한 값이 첫 페이지이다.<br>
     * (페이지 그룹*페이지 그룹 개수, 그룹의 마지막 번호이므로) <br>
     * 페이지 그룹 <br>
     * 1 2 3 4 || 5 6 7 8 || 9 10 <br>
     *
     * ex) 현재 페이지 8이면 이전 페이지 그룹의 마지막 번호 4이고 여기에 +1 하면 시작페이지 번호가 됨
     * @return
     */
    public int getStartPageOfPageGroup() {
        //
        return (this.getNowPageGroup()-1) * this.pageCountPerPageGroup + 1;
    }

    /**
     * 현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 return 한다.<br>
     * 1. 현재 페이지 그룹 * 페이지 그룹 개수 가 마지막 번호이다. <br>
     * 2. 그 그룹의 마지막 페이지 번호가 전체 페이지의 마지막 페이지 번호보다 <br>
     * 큰 경우는 전체 페이지의 마지막 번호를 return 한다.<br>
     * 1 2 3 4 || 5 6 7 8 || 9 10
     *  1그룹        2그룹       3그룹
     * @return
     */
    public int getEndPageOfPageGroup() {
        int endPage = this.getNowPageGroup()*this.pageCountPerPageGroup;

        if(endPage > this.getTotalPage()) {
            endPage = this.getTotalPage();
        }

        return endPage;
    }

    /**
     * 이전 페이지 그룹이 있는지 체크하는 메서드 <br>
     * 현재 페이지가 속한 페이지 그룹이 1보다 크면 true<br>
     * ex ) 페이지 1 2 3 4 / 5 6 7 8 / 9 10 <br>
     * 1 2 3 group
     *
     * @return
     */
    public boolean isPreviousPageGroup() {
        boolean flag = false;

        if(this.getNowPageGroup() > 1) {
            flag = true;
        }

        return flag;
    }

    /**
     * 다음 페이지 그룹이 있는지 체크하는 메서드 <br>
     * 현재 페이지 그룹이 마지막 페이지 그룹(마지막 페이지 그룹 == 총 페이지 그룹 수) 보다 작으면 true<br>
     * * ex ) 페이지 <br>
     * 1 2 3 4 / 5 6 7 8 / 9 10 <br>
     * 1 2 3 group
     *
     * @return
     */
    public boolean isNextPageGroup() {
        boolean flag = false;

        if(this.getNowPageGroup() < this.getTotalPageGroup()) {
            flag = true;
        }

        return flag;
    }
}
