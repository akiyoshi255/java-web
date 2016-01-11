QUnit.test("hello test", function(assert) {
	assert.ok(1 == "1", "Passed!");
});

QUnit.test("validateInteger test", function(assert) {
	assert.equal(validateInteger("-1", 1, 100), false);
	assert.equal(validateInteger("0", 1, 100), false);
	assert.equal(validateInteger("1", 1, 100), true);
	assert.equal(validateInteger("2", 1, 100), true);
	assert.equal(validateInteger("99", 1, 100), true);
	assert.equal(validateInteger("100", 1, 100), true);
	assert.equal(validateInteger("101", 1, 100), false);
	assert.equal(validateInteger("102", 1, 100), false);
	assert.equal(validateInteger(NaN, 1, 100), false);

	assert.equal(validateInteger(" 50", 1, 100), true);
	assert.equal(validateInteger("50 ", 1, 100), true);
	assert.equal(validateInteger(" 50 ", 1, 100), true);

	assert.equal(validateInteger(null, 1, 100), false);
	assert.equal(validateInteger(undefined, 1, 100), false);
	assert.equal(validateInteger("", 1, 100), false);
	assert.equal(validateInteger("f", 1, 100), false);
	assert.equal(validateInteger("foo", 1, 100), false);
	assert.equal(validateInteger("1.2", 1, 100), false);
});

QUnit.test("convertToDate test", function(assert) {
	var date = convertToDate("1970/01/01");
	assert.equal(date.getFullYear(), "1970");
	assert.equal(date.getMonth(), "0", "月は0〜11で指定する。");
	assert.equal(date.getDate(), "01");

	var date = convertToDate("2099/11/30");
	assert.equal(date.getFullYear(), "2099");
	assert.equal(date.getMonth(), "10", "月は0〜11で指定する。");
	assert.equal(date.getDate(), "30");

	assert.equal(convertToDate("1970/01", null));
});

QUnit.test("checkPeriod test", function(assert) {
	assert.ok(checkPeriod(new Date(2000, 0, 1), new Date(2000, 0, 2)));
	assert.notOk(checkPeriod(new Date(2000, 0, 2), new Date(2000, 0, 1)));
});
