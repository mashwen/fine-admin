package utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class PageUtil {
    private Integer countPerPage;
    private Integer nextPage;
    private Integer page;
    private Integer prevPage;
    private Integer totalCount;
    private Integer totalPage;

    public List pageUtil(List list,Integer pageNum,Integer pageSize){
        if(list == null){
            return null;
        }
        if(list.size() == 0){
            return null;
        }

        this.totalCount = list.size(); //记录总数
        this.countPerPage = pageSize;//每页几条
        this.page = pageNum;//当前页数
        this.totalPage = (int) Math.ceil(new Double(totalCount/pageSize))+1;//总页数
        log.info("总页数===="+this.totalPage);
        if (pageNum==1){
            this.prevPage = 0;
        }else {
            this.prevPage = pageNum-1;
        }

        if (pageNum==totalPage){
            this.nextPage = 0;
        }else {
            this.nextPage = pageNum+1;
        }

        int fromIndex = 0; //开始索引
        int toIndex = 0; //结束索引

        if (pageNum != totalPage) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = totalCount;
        }

        List pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }

}
