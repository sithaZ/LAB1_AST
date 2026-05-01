class CartPage {
  constructor(page) {
    this.page = page;
    this.cartItem = page.locator('.cart_item');
  }

  async verifyItemInCart() {
    await this.cartItem.first().waitFor();
  }
}

module.exports = { CartPage };