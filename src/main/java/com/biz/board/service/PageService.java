package com.biz.board.service;

import org.springframework.stereotype.Service;

import com.biz.board.domain.PageVO;

@Service
public class PageService {

	private int listPerPage = 10;
	private int pageCount = 5;
	
	public PageVO getPagination(int totalCount, int currentPageNo) {
		
		if(totalCount < 1) {
			return null;
		}
		
		if(currentPageNo < 1) {
			currentPageNo = 1;
		}
		
		int finalPageNo = totalCount / listPerPage;
		
		if(totalCount % listPerPage > 0) {
			finalPageNo ++;
		}
		
		if(finalPageNo < currentPageNo) {
			currentPageNo = finalPageNo;
		}
		
		int startPageNo = ((currentPageNo - 1) / 10) * 10 + 1;
		int endPageNo = startPageNo + pageCount - 1;
		if(endPageNo > finalPageNo) {
			endPageNo = finalPageNo;
		}
		
		int prePageNo = 1;
		if(currentPageNo > 1) {
			prePageNo = currentPageNo - 1;
		}
		
		int nextPageNo = finalPageNo;
		if(currentPageNo < finalPageNo) {
			nextPageNo = currentPageNo + 1;
		}
		
		int offset = (currentPageNo - 1) * listPerPage + 1;
		int limit = offset + listPerPage - 1;
		
		PageVO pageVO = PageVO.builder().currentPageNo(currentPageNo)
										.endPageNo(endPageNo)
										.finalPageNo(finalPageNo)
										.firstPageNo(1)
										.listPerPage(listPerPage)
										.nextPageNo(nextPageNo)
										.offset(offset)
										.limit(limit)
										.pageCount(pageCount)
										.prePageNo(prePageNo)
										.startPageNo(startPageNo)
										.totalCount(totalCount)
										.build();
		
		return pageVO;
		
	}
	
	
	
}
