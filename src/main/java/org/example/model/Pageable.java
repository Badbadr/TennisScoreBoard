package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pageable<T> {
    public List<T> elements;
    public int page = 0;
    public int pageSize = 10;
    public int totalPages;
    public int totalElements;
}
