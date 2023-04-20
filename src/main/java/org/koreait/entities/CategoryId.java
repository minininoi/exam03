package org.koreait.entities;

import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@IdClass(Category.class)
public class CategoryId implements Serializable {
    private String cateCd;
    private String SubCateAd;
}
