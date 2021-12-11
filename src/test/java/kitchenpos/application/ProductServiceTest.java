package kitchenpos.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.application.product.ProductService;
import kitchenpos.domain.Price;
import kitchenpos.domain.product.Product;
import kitchenpos.domain.product.ProductRepository;
import kitchenpos.dto.ProductDto;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product 뿌링클치킨;
    private Product 치킨무;
    private Product 코카콜라;

    @BeforeEach
    void setUp() {
        뿌링클치킨 = Product.of("뿌링클치킨", Price.of(15_000));
        치킨무 = Product.of("치킨무", Price.of(1_000));
        코카콜라 = Product.of("코카콜라", Price.of(3_000));
    }

    @DisplayName("상품이 저장된다.")
    @Test
    void create_product() {
        // given
        when(productRepository.save(any(Product.class))).thenReturn(this.뿌링클치킨);

        // when
        ProductDto createdProduct = productService.create(ProductDto.of(this.뿌링클치킨));

        // then
        Assertions.assertThat(createdProduct).isEqualTo(ProductDto.of(this.뿌링클치킨));
    }

    @DisplayName("상품이 조회된다.")
    @Test
    void search_product() {
        // given
        when(productRepository.findAll()).thenReturn(List.of(this.뿌링클치킨, this.치킨무, this.코카콜라));

        // when
        List<ProductDto> searchedProducts = productService.list();

        // then
        Assertions.assertThat(searchedProducts).isEqualTo(List.of(ProductDto.of(this.뿌링클치킨), ProductDto.of(this.치킨무), ProductDto.of(this.코카콜라)));
    }
}
