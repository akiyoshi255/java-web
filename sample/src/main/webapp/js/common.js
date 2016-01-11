function validateInteger(text, minValue, maxValue) {
	if (!text) {
		return false;
	}

	text = text.trim();
	if (!text.match(/^[0-9]+$/)) {
		return false;
	}

	var value = parseInt(text);
	if (value < minValue) {
		return false;
	}
	if (value > maxValue) {
		return false;
	}
	return true;
}

function convertToDate(text) {
	if (!text) {
		return null;
	}

	// yyyy/MM/ddの形式かどうかをチェックする。
	text = text.trim();
	var results = text.match(/^([0-9]{4})\/([01][0-9])\/([0123][0-9])$/);
	if (results == null) {
		return null;
	}
	var year = results[1];
	var month = results[2];
	var day = results[3];
	return new Date(year, month - 1, day); // 月は0〜11の範囲で指定する。
}

function checkPeriod(startDate, endDate) {
	if (!startDate) {
		return null;
	}
	if (!endDate) {
		return null;
	}

	var startTime = startDate.getTime();
	var endTime = endDate.getTime();
	if (startTime > endTime) {
		return null;
	}
	return true;
}
