const { test, expect } = require('@playwright/test');
const { LoginPage } = require('../pages/LoginPage');

test('user can login and see products', async ({ page }, testInfo) => {
  const loginPage = new LoginPage(page);

  await loginPage.goto();
  await loginPage.login('standard_user', 'secret_sauce');

  await expect(page.locator('.title')).toHaveText('Products');

  await page.screenshot({
    path: `screenshots/login-success.png`,
    fullPage: true
  });

  await testInfo.attach('Login Success Screenshot', {
    path: 'screenshots/login-success.png',
    contentType: 'image/png'
  });
});