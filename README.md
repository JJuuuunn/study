<a href="https://docs.google.com/spreadsheets/d/e/2PACX-1vS2xmtiAHll3CmfNeDH_EmWwW6Ey5YPF97oy7LNwv40VIPda0EKZd_SEOQUk9ptLE1nRdMSSRQdGUtV/pubhtml">Go to Google Sheet</a>

<style>
  table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: center;
  }
  th {
    background-color: #f2f2f2;
  }
</style>

<h2>CONTROLLER</h2>
<table>
  <tr>
    <th>HTTP Type</th>
    <th>URL</th>
    <th>Method Name</th>
    <th>Parameters</th>
    <th>Return Value</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>POST</td>
    <td>/products/add</td>
    <td>addProduct</td>
    <td>@RequestBody AddProductDTO productDTO</td>
    <td>void</td>
    <td>상품을 추가합니다.</td>
  </tr>
  <tr>
    <td>GET</td>
    <td>/products/{id}</td>
    <td>getProductById</td>
    <td>@PathVariable("id") Long id</td>
    <td>ProductDTO</td>
    <td>지정된 ID의 상품을 조회합니다.</td>
  </tr>
  <tr>
    <td>GET</td>
    <td>/products/all</td>
    <td>getAllProducts</td>
    <td>-</td>
    <td>List&lt;ProductDTO&gt;</td>
    <td>모든 상품 목록을 조회합니다.</td>
  </tr>
  <tr>
    <td>GET</td>
    <td>/products/search</td>
    <td>searchProductsByName</td>
    <td>@RequestParam("name") String name</td>
    <td>List&lt;ProductDTO&gt;</td>
    <td>이름으로 상품을 검색합니다.</td>
  </tr>
  <tr>
    <td>PUT</td>
    <td>/products/{id}</td>
    <td>updateProduct</td>
    <td>@PathVariable("id") Long id, @RequestBody UpdateProductDTO productDTO</td>
    <td>void</td>
    <td>상품 정보를 수정합니다.</td>
  </tr>
  <tr>
    <td>DELETE</td>
    <td>/products/{id}</td>
    <td>deleteProduct</td>
    <td>@PathVariable("id") Long id</td>
    <td>void</td>
    <td>지정된 ID의 상품을 삭제합니다.</td>
  </tr>
</table>

<h2>SERVICE</h2>
<table>
  <tr>
    <th>Access Modifier</th>
    <th>Method</th>
    <th>Return Value</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>public</td>
    <td>addProduct</td>
    <td>void</td>
    <td>제품을 추가합니다.</td>
  </tr>
  <tr>
    <td>public</td>
    <td>searchProductById</td>
    <td>ProductDTO</td>
    <td>제품 번호로 제품을 검색합니다.</td>
  </tr>
  <tr>
    <td>public</td>
    <td>searchProductByName</td>
    <td>List&lt;ProductDTO&gt;</td>
    <td>제품 이름으로 제품을 검색합니다.</td>
  </tr>
  <tr>
    <td>public</td>
    <td>getAllProducts</td>
    <td>List&lt;ProductDTO&gt;</td>
    <td>모든 제품 목록을 반환합니다.</td>
  </tr>
  <tr>
    <td>public</td>
    <td>updateProduct</td>
    <td>void</td>
    <td>제품 정보를 업데이트합니다.</td>
  </tr>
  <tr>
    <td>public</td>
    <td>removeProduct</td>
    <td>void</td>
    <td>제품을 삭제합니다.</td>
  </tr>
</table>

<h2>MAPPER</h2>
<table>
  <tr>
    <th>Access Modifier</th>
    <th>Method</th>
    <th>Return Value</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>public</td>
    <td>findProductById</td>
    <td>Product</td>
    <td>상품을 조회합니다.</td>
  </tr>
  <tr>
    <td>public</td>
    <td>findAllProducts</td>
    <td>List&lt;Product&gt;</td>
    <td>전체 상품 목록을 조회합니다.</td>
  </tr>
  <tr>
    <td>public</td>
    <td>findProductsByName</td>
    <td>List&lt;Product&gt;</td>
    <td>상품 이름으로 상품을 검색합니다.</td>
  </tr>
  <tr>
    <td>public</td>
    <td>addProduct</td>
    <td>void</td>
    <td>상품을 추가합니다.</td>
  </tr>
  <tr>
    <td>public</td>
    <td>updateProduct</td>
    <td>void</td>
    <td>상품 정보를 수정합니다.</td>
  </tr>
  <tr>
    <td>public</td>
    <td>deleteProduct</td>
    <td>void</td>
    <td>상품을 제거합니다.</td>
  </tr>
</table>

<h2>DOMAIN</h2>
<table>
  <tr>
    <th>Data Type</th>
    <th>Field</th>
    <th>Description</th>
    <th>Annotations</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Long</td>
    <td>productId</td>
    <td>제품 번호</td>
    <td>@NotNull</td>
    <td>제품 ID는 null이 될 수 없습니다.</td>
  </tr>
  <tr>
    <td>String</td>
    <td>productName</td>
    <td>제품 이름</td>
    <td>@NotBlank, @Size(max = 100)</td>
    <td>제품 이름은 공백일 수 없으며, 최대 100자까지 가능합니다.</td>
  </tr>
  <tr>
    <td>double</td>
    <td>price</td>
    <td>제품 가격</td>
    <td>@Positive</td>
    <td>가격은 양수여야 합니다.</td>
  </tr>
  <tr>
    <td>int</td>
    <td>inventoryQuantity</td>
    <td>제품 재고 수량</td>
    <td>@PositiveOrZero</td>
    <td>재고 수량은 0 이상이어야 합니다.</td>
  </tr>
</table>

<h2>DTO</h2>

<h3>AddProductDTO</h3>
<table>
  <tr>
    <th>Data Type</th>
    <th>Field</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>String</td>
    <td>productName</td>
    <td>제품 이름</td>
  </tr>
  <tr>
    <td>double</td>
    <td>price</td>
    <td>제품 가격</td>
  </tr>
  <tr>
    <td>int</td>
    <td>inventoryQuantity</td>
    <td>제품 재고 수량</td>
  </tr>
</table>

<h3>UpdateProductDTO</h3>
<table>
  <tr>
    <th>Data Type</th>
    <th>Field</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Long</td>
    <td>productId</td>
    <td>제품 번호</td>
  </tr>
  <tr>
    <td>String</td>
    <td>productName</td>
    <td>제품 이름</td>
  </tr>
  <tr>
    <td>double</td>
    <td>price</td>
    <td>제품 가격</td>
  </tr>
  <tr>
    <td>int</td>
    <td>inventoryQuantity</td>
    <td>제품 재고 수량</td>
  </tr>
</table>

<h3>ProductInfoDTO</h3>
<table>
  <tr>
    <th>Data Type</th>
    <th>Field</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Long</td>
    <td>productId</td>
    <td>제품 식별자</td>
  </tr>
  <tr>
    <td>String</td>
    <td>productName</td>
    <td>제품 이름</td>
  </tr>
  <tr>
    <td>double</td>
    <td>price</td>
    <td>제품 가격</td>
  </tr>
  <tr>
    <td>int</td>
    <td>inventoryQuantity</td>
    <td>제품 재고 수량</td>
  </tr>
</table>

<h3>SearchProductDTO</h3>
<table>
  <tr>
    <th>Data Type</th>
    <th>Field</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Long</td>
    <td>productId</td>
    <td>제품 식별자</td>
  </tr>
  <tr>
    <td>String</td>
    <td>productName</td>
    <td>제품 이름</td>
  </tr>
</table>

<h2>SQL SCRIPT (MYSQL)</h2>
<p>아래는 제품 정보를 저장하는 테이블의 SQL 스크립트입니다.</p>
<pre>
CREATE TABLE Product (
  productId     BIGINT          NOT NULL AUTO_INCREMENT                                         COMMENT '제품 ID',
  productName   VARCHAR(100)    NOT NULL UNIQUE                                                 COMMENT '제품명',
  price         INT             NOT NULL CHECK (price >= 0 AND price <= 1000000)                COMMENT '가격 (원)',
  stockQuantity INT             NOT NULL CHECK (stockQuantity >= 0 AND stockQuantity <= 99999)  COMMENT '재고 수량',

  PRIMARY KEY (productId)
) ENGINE=InnoDB 
  DEFAULT CHARSET=utf8mb4 
  COLLATE=utf8mb4_unicode_ci 
  COMMENT='제품 정보';
</pre>
