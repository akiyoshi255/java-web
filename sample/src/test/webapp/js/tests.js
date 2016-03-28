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

QUnit.test("JSonTest", function(assert) {
    var text = '{ "key1": "value1", "key2": "value2"}';
    var obj = $.parseJSON(text);
    assert.equal(obj["key1"], "value1");
    assert.equal(obj["key2"], "value2");
});

QUnit.test("JSonTest", function(assert) {
    var text = '["key1", "value1", "key2", "value2"]';
    var obj = $.parseJSON(text);
    assert.equal(obj[0], "key1");
    assert.equal(obj[1], "value1");
    assert.equal(obj[2], "key2");
    assert.equal(obj[3], "value2");
});

var User = function() {
    this.name = null;

    this.setData = function(obj) {
        this.name = obj["name"];
    }

    this.setName = function(name) {
        this.name = name;
    }

    this.getName = function() {
        return this.name;
    }
};

QUnit.test("JSonTest", function(assert) {
    var text = '{"name": "foo"}';
    var obj = $.parseJSON(text);
    assert.equal(obj.name, "foo");

    var user = new User();
    assert.equal(user.name, null);

    user.setName("bar");
    assert.equal(user.name, "bar");
    assert.equal(user.getName(), "bar");

    user.setData(obj);
    assert.equal(user.name, "foo");
    assert.equal(user.getName(), "foo");
});

QUnit.test("Date Test", function(assert) {
    var date = new Date(2016, 0, 30);
    assert.equal(date.getDate(), 30);
    // assert.equal(date.toString(), "2016/1/30");
});

QUnit.test("Obj Test", function(assert) {
    var obj = {};
    obj.name = "foo";
    assert.ok(obj.name, "foo");
});
