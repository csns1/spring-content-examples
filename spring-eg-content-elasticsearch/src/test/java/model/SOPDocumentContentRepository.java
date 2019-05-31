package model;


import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.commons.search.Searchable;

public interface SOPDocumentContentRepository extends ContentStore<SOPDocument, Integer>, Searchable<Integer> {
}
