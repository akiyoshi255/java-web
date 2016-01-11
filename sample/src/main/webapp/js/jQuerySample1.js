jQuery(function($) {
	console.log("jQuery!");

	var paramsChanged = function() {
		console.log("paramsChanged!");

		// ボタン非活性フラグ
		var enabled = true;

		// 最大数(指定可能値：1〜100)
		var maxSegmentNum = $("#maxSegmentNum").val();
		var maxSegmentNumErrorMessage = $("#maxSegmentNumErrorMessage");
		if (validateInteger(maxSegmentNum, 1, 100)) {
			maxSegmentNumErrorMessage.empty();
		} else {
			maxSegmentNumErrorMessage.text("エラー");
			enabled = false;
		}

		// 対象期間
		var startDateText = $("#startDate").val();
		var startDate = convertToDate(startDateText);
		var endDateText = $("#endDate").val();
		var endDate = convertToDate(endDateText);
		var targetPeriodErrorMessage = $("#targetPeriodErrorMessage");
		var isValidTargetPeriod = true;
		if (startDate == null) {
			targetPeriodErrorMessage.text("開始日付フォーマットエラー");
			isValidTargetPeriod = false;
			enabled = false;
		}
		if (isValidTargetPeriod && (endDate == null)) {
			targetPeriodErrorMessage.text("終了日付フォーマットエラー");
			isValidTargetPeriod = false;
			enabled = false;
		}
		if (isValidTargetPeriod && (!checkPeriod(startDate, endDate))) {
			targetPeriodErrorMessage.text("開始日付が、終了日付より後");
			isValidTargetPeriod = false;
			enabled = false;
		}
		if (isValidTargetPeriod) {
			targetPeriodErrorMessage.empty();
		}

		// 最低数（指定可能値：1〜1000）
		var minItemTypeNum = $("#minItemTypeNum").val();
		var minItemTypeNumErrorMessage = $("#minItemTypeNumErrorMessage");
		if (validateInteger(minItemTypeNum, 1, 1000)) {
			minItemTypeNumErrorMessage.empty();
		} else {
			minItemTypeNumErrorMessage.text("エラー");
			enabled = false;
		}

		// ロジック固有の条件
		logic = $("#logicSelectBox").val()
		if (logic === "logic1") {
			// ロジック1閾値(指定可能値：1〜500)
			var logic1Threshold = $("#logic1Threshold").val();
			var logic1ThresholdErrorMessage = $("#logic1ThresholdErrorMessage");
			if (validateInteger(logic1Threshold, 1, 500)) {
				logic1ThresholdErrorMessage.empty();
			} else {
				logic1ThresholdErrorMessage.text("エラー");
				enabled = false;
			}

			// 最低点数(指定可能値：1〜1000)
			var minItemNum = $("#minItemNum").val();
			var minItemNumErrorMessage = $("#minItemNumErrorMessage");
			if (validateInteger(minItemNum, 1, 1000)) {
				minItemNumErrorMessage.empty();
			} else {
				minItemNumErrorMessage.text("エラー");
				enabled = false;
			}
		} else if (logic === "logic2") {
			// ロジック2閾値
			var logic2Threshold = $("#logic2Threshold").val();
			var logic2ThresholdErrorMessage = $("#logic2ThresholdErrorMessage");
			if (validateInteger(logic2Threshold, 1, 1000)) {
				logic2ThresholdErrorMessage.empty();
			} else {
				logic2ThresholdErrorMessage.text("エラー");
				enabled = false;
			}
		} else {
			console.log("ERROR: must not happen. (logic=" + logic + ")");
		}

		// 途中でエラーが発生していた場合は非活性化
		$("#execSegmentation").prop("disabled", !enabled);
	};

	// ロジック
	var logic1Param = $(".logic1Param");
	var logic2Param = $(".logic2Param");
	var logicChanged = function() {
		logic = $("#logicSelectBox").val()
		if (logic === "logic1") {
			logic2Param.remove();
			logic1Param.appendTo("#paramList");

			// ロジック1閾値(指定可能値：1〜500)
			$("#logic1Threshold").on("change", paramsChanged);
			// 最低購買点数(指定可能値：1〜1000)
			$("#minItemNum").on("change", paramsChanged);
		} else if (logic === "logic2") {
			logic1Param.remove();
			logic2Param.appendTo("#paramList");

			// ロジック2閾値(指定可能値：1〜1000)
			$("#logic2Threshold").on("change", paramsChanged);
		} else {
			console.log(logic);
			console.log("ERROR: must not happen.");
		}
	};

	// ロジック
	$("#logicSelectBox").on("change", function() {
		logicChanged();
		paramsChanged();
	});
	// 最大セグメント数(指定可能値：1〜100)
	$("#maxSegmentNum").on("change", paramsChanged);
	// 対象期間
	$("#startDate").on("change", paramsChanged);
	$("#endDate").on("change", paramsChanged);
	// 最低購買商品数(指定可能値：1〜1000)
	$("#minItemTypeNum").on("change", paramsChanged);

	// 初期化
	logicChanged();
	paramsChanged();

	// セグメント付与実行
	$("#execSegmentation").on("click", function() {
		// 共通条件
		var logic = $("#logicSelectBox").val();
		var maxSegmentNum = $("#maxSegmentNum").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var minItemTypeNum = $("#minItemTypeNum").val();
		// 条件1
		var logic1Threshold = $("#logic1Threshold").val();
		var minItemNum = $("#minItemNum").val();
		// 条件2
		var logic2Threshold = $("#logic2Threshold").val();

		console.log("logic=" + logic);
		console.log("maxSegmentNum=" + maxSegmentNum);
		console.log("startDate=" + startDate + "-" + endDate);
		console.log("minItemTypeNum=" + minItemTypeNum);
		console.log("logic1Threshold=" + logic1Threshold);
		console.log("minItemNum=" + minItemNum);
		console.log("logic2Threshold=" + logic2Threshold);

		// 送信
		$.ajax({
			url : "ajaxSample",
			type : "POST",
			data : {
				"logic" : logic,
				"maxSegmentNum" : maxSegmentNum,
				"startDate" : startDate,
				"endDate" : endDate,
				"minItemTypeNum" : minItemTypeNum,
				"logic1Threshold" : logic1Threshold,
				"minItemNum" : minItemNum,
				"logic2Threshold" : logic2Threshold
			},
			dataType : "json",
			timeout : 3600000
		}).done(function(data, status, xhr) {
			// 通信成功時の処理
			console.log("done");
			for (key in data) {
				console.log(key + "=" + data[key]);
			}
			console.log("status=" + status);
			console.log("xhr=" + xhr);
			console.log("xhr.responseText=" + xhr.responseText);
		}).fail(function(xhr, status, error) {
			// 通信失敗時の処理
			console.log("fail");
			console.log("error=" + error);
			console.log("status=" + status);
			console.log("xhr=" + xhr);
			console.log("xhr.responseText=" + xhr.responseText);
		}).always(function(arg1, status, arg2) {
			// 通信完了時の処理
			console.log("always");
		});
		return false;
	});
});