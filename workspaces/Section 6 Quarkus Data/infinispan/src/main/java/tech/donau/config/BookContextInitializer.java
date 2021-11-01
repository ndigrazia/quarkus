package tech.donau.config;


import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

import tech.donau.data.Book;

@AutoProtoSchemaBuilder(includeClasses = {Book.class}, schemaPackageName = "book_sample")
public interface  BookContextInitializer extends SerializationContextInitializer {
}
