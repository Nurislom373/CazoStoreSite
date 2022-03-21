package com.st2emx.online_store.dto.product;

import com.st2emx.online_store.dto.productColor.ProductColorNameDto;
import com.st2emx.online_store.dto.productComment.ProductCommentNameDto;
import com.st2emx.online_store.dto.productImage.ImagePathDto;
import com.st2emx.online_store.dto.productSize.ProductSizeNameDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFullDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean is_new;
    private List<ImagePathDto> images;
    private List<ProductSizeNameDto> sizes;
    private List<ProductColorNameDto> colors;
    private List<ProductCommentNameDto> comments;
}
