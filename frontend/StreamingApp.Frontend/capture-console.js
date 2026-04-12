const { chromium } = require('playwright');
(async () => {
  const browser = await chromium.launch();
  const page = await browser.newPage();
  page.on('console', msg => {
    console.log('CONSOLE', msg.type(), msg.text());
  });
  page.on('pageerror', err => {
    console.log('PAGEERROR', err.message);
  });
  page.on('requestfailed', request => {
    console.log('REQUESTFAILED', request.url(), request.failure()?.errorText);
  });
  try {
    const response = await page.goto('http://127.0.0.1:5165', { waitUntil: 'networkidle' });
    console.log('STATUS', response.status());
    await page.waitForTimeout(5000);
  } catch (e) {
    console.error('ERROR', e);
  } finally {
    await browser.close();
  }
})();
