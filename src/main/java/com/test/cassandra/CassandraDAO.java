package com.test.cassandra;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.test.cassandra.entity.CassandraItem;

@Repository
public class CassandraDAO {
	@Autowired
	private Session session;
	
	private PreparedStatement writePreparedStatement;
	private PreparedStatement readAllPreparedStatement;
	
	@PostConstruct
	private void init() throws Exception {
		this.setWritePreparedStatement( this.getSession().prepare("INSERT INTO simple (id, key) VALUES (?,?);") );
		this.setReadAllPreparedStatement( this.getSession().prepare("SELECT * FROM simple;") );
	}
	
	public void write(CassandraItem item) {
		this.getSession().execute(  this.getWritePreparedStatement().bind( item.getId(), item.getKey() ) );
	}
	
	public List<CassandraItem> readAll() {
		List<CassandraItem> items = new ArrayList<CassandraItem>();
		ResultSet result = this.getSession().execute( this.getReadAllPreparedStatement().bind() );
		
		result.forEach( row -> {
			CassandraItem item = new CassandraItem();
			
			item.setKey( row.getString("key") );
			item.setId( row.getUUID("id") );
			
			items.add(item);
		});
		
		return items;
	}

	public PreparedStatement getWritePreparedStatement() {
		return writePreparedStatement;
	}
	public void setWritePreparedStatement(PreparedStatement writePreparedStatement) {
		this.writePreparedStatement = writePreparedStatement;
	}

	public PreparedStatement getReadAllPreparedStatement() {
		return readAllPreparedStatement;
	}
	public void setReadAllPreparedStatement(PreparedStatement readAllPreparedStatement) {
		this.readAllPreparedStatement = readAllPreparedStatement;
	}

	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
}
