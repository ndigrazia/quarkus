package org.acme.dynamodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;


@ApplicationScoped
public class BookService {

    public static final String TITLE_COL = "title";
    public static final String PAGES_COL = "pages";

    public static final String TABLE = "Books";
    
    @Inject
    DynamoDbClient client;

    private ScanRequest scan() {
       return  ScanRequest.builder().tableName(TABLE).attributesToGet(TITLE_COL, PAGES_COL).build();
    }

    public Book get(String title) {
        return Book.from(client.getItem(getItem(title)).item());
    }

    private GetItemRequest getItem(String item) {
        final Map<String, AttributeValue> items = new HashMap<String, AttributeValue>();
        items.put(TITLE_COL, AttributeValue.builder().s(item).build());

        return GetItemRequest.builder().tableName(TABLE).key(items).attributesToGet(TITLE_COL, PAGES_COL).build();
    }

    private PutItemRequest put(Book b) {
        final Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put(TITLE_COL, AttributeValue.builder().s(b.getTitle()).build());
        item.put(PAGES_COL, AttributeValue.builder().n(b.getPages().toString()).build());

        return PutItemRequest.builder().tableName(TABLE).item(item).build();
    }

    public List<Book> findAll() {
        return client.scanPaginator(scan()).items().stream().map(Book::from).collect(Collectors.toList());
    }

    public void add(Book b) {
        client.putItem(put(b));
    }
}
