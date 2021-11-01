package tech.donau.config;

import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurationContext;
import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurer;

public class AnalysisConfigurer implements ElasticsearchAnalysisConfigurer{

    public void configure(ElasticsearchAnalysisConfigurationContext var1) {
        var1.analyzer("name").custom().tokenizer("standard").tokenFilters("asciifolding", "lowercase");    
        var1.analyzer("english").custom().tokenizer("standard").tokenFilters("asciifolding", "lowercase", "porter_stem");    
        var1.normalizer("sort").custom().tokenFilters("asciifolding", "lowercase");    
    }
    
}
