class ProductsPage {
  constructor(page) {
    this.page = page;
    this.title = page.locator('.title');
    this.addToCartBtn = page.locator('#add-to-cart-sauce-labs-backpack');
    this.cartIcon = page.locator('.shopping_cart_link');
  }

  async verifyPageLoaded() {
    await this.title.waitFor();
  }

  async addProductToCart() {
    await this.addToCartBtn.click();
  }

  async goToCart() {
    await this.cartIcon.click();
  }
}

module.exports = { ProductsPage };