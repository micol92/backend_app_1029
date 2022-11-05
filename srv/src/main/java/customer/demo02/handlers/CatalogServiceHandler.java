package customer.demo02.handlers;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.catalogservice.CatalogService_;
import cds.gen.catalogservice.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.CallableStatementCreator;
import cds.gen.catalogservice.*;

import com.sap.cds.Result;
import com.sap.cds.Struct;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnInsert;

import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.messages.Messages;
import com.sap.cds.services.persistence.PersistenceService;
import static cds.gen.catalogservice.CatalogService_.BOOKS;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {
	
    @Autowired
    private  PersistenceService db;

   // CatalogServiceHandler() {}

//    @On(entity = Books_.CDS_NAME)
    @On(event = SaveBookContext.CDS_NAME)
	public void onSaveBook(SaveBookContext context) {

        System.out.println("zzzzzzzzzzzzzzzzzzzz1");
        Map<String, Object> book = new HashMap<>();
        book.put("ID", 101);
        book.put("title", "Capire 2");
        book.put("stock", 100);
        System.out.println("zzzzzzzzzzzzzzzzzzzz2");

        CqnInsert insert = Insert.into("CatalogService.Books").entry(book);
        db.run(insert);
        System.out.println("zzzzzzzzzzzzzzzzzzzz3");
        context.setCompleted();

	}  

	@After(event = CqnService.EVENT_READ)
	public void discountBooks(Stream<Books> books) {
		books.filter(b -> b.getTitle() != null).forEach(b -> {
			//loadStockIfNotSet(b);
			//discountBooksWithMoreThan111Stock(b);
		});
	}
    
/*
    @On(event = SaveBookTypesEntityProcContext.CDS_NAME)
    public void onSaveBookTypesEntityProc(SaveBookTypesEntityProcContext context) {

        
        Integer pId = 1 ; //context.Id();
		String pTitle = "tttt"; //context.Title();
		Integer pStock = 1; //context.Stock();

        Collection<BookTypes> v_result = new ArrayList<>();

		//String bookId = (String) analyzer.analyze(context.getCqn()).targetKeys().get(Books.ID);

		cds.gen.catalogservice.Books books = cds.gen.catalogservice.Books.create();
		books.setId(pId);
        books.setTitle(pTitle);
        books.setStock(pStock); 
        
		Result res = db.run(Insert.into(CatalogService_.BOOKS).entry(books));
		cds.gen.catalogservice.Books inserted = res.single(cds.gen.catalogservice.Books.class);

        context.setResult(1);
		//messages.success(MessageKeys.REVIEW_ADDED);

		//context.setResult(Struct.access(inserted).as(Books.class));


        }    
*/

}