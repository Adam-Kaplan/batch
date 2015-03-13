package com.test.batch.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.jaxrs.IRESTProxy;
import com.test.jaxrs.entity.RESTItem;
import com.test.jaxrs.entity.RESTResult;

@Component
public class RESTItemReader implements ItemStreamReader<RESTItem> {
	public static final String REST_ITEM_INDEX_TRACKER = "com.test.batch.reader.RESTItemReader#itemIndex";
	public static final int REST_ITEM_FIRST_INDEX = 0;
	
	@Autowired
	private IRESTProxy restProxy;
	
	private RESTResult restResult;
	private int itemIndex;

	@Override
	public void open(ExecutionContext executionContext)	throws ItemStreamException {
		if (executionContext.containsKey( REST_ITEM_INDEX_TRACKER )) {
			throw new ItemStreamException( "Web service has already been called." );
		} else {
			this.setRestResult( this.getRestProxy().callWS() );
			this.setItemIndex( REST_ITEM_FIRST_INDEX );
			executionContext.put( REST_ITEM_INDEX_TRACKER , this.getItemIndex() );
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		if (executionContext.containsKey( REST_ITEM_INDEX_TRACKER )) {
			executionContext.put( REST_ITEM_INDEX_TRACKER, executionContext.getInt(REST_ITEM_INDEX_TRACKER)+1 );
		} else {
			throw new ItemStreamException( "Update requested before web service has been called." );
		}
	}

	@Override
	public void close() throws ItemStreamException {
		this.setRestResult( null );
		this.setItemIndex( REST_ITEM_FIRST_INDEX );
	}

	@Override
	public RESTItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		RESTResult result = this.getRestResult();
		if (result == null) {
			throw new UnexpectedInputException( "Data not avaliable from web service." );
		} else {
			int index = this.getItemIndex();
			if (index < result.getData().getItems().size()) {
				return result.getData().getItems().get(index);
			} else {
				// if at end of list, return null
				return null;
			}
		}
	}
	
	public IRESTProxy getRestProxy() {
		return restProxy;
	}
	public void setRestProxy(IRESTProxy restProxy) {
		this.restProxy = restProxy;
	}

	public RESTResult getRestResult() {
		return restResult;
	}
	public void setRestResult(RESTResult restResult) {
		this.restResult = restResult;
	}

	public int getItemIndex() {
		return itemIndex;
	}
	public void setItemIndex(int itemIndex) {
		this.itemIndex = itemIndex;
	}

}
