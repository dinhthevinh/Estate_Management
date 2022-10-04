<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api/building" />
<c:url var="formUrl" value="/api/user" />
<c:url var="redirect" value="/admin/list" />
<html>
<head>
<title>Chỉnh sửa tòa nhà</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>
					<li class="active">Dashboard</li>
				</ul>
				<!-- /.breadcrumb -->

			</div>

			<div class="page-content">

				<div class="row">
					<div class="col-xs-12">
						<form:form modelAttribute="modelSearch" action="" id="formEdit"
							method="GET" cssClass="form-horizontal">
							<!-- 						<form class="form-horizontal" role="form" id="formEdit"> -->
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-left" for="name">
									Tên sản phẩm</label>

								<div class="col-sm-9">
									<input type="text" id="name" class="form-control" name="name"
										value="${buildingModel.name}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="district">Quận hiện có</label> 
								<div class="col-sm-2">	
								<select id="district" name="district">							
									<c:forEach var="item" items="${districts}">
										<option value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
								</div>

								<!-- 								<label class="col-sm-3 control-label no-padding-right" -->
								<!-- 									for="district">Quận hiện có</label> -->
								<!-- 								<div class="col-sm-2"> -->
								<%-- 									<form:select cssClass="form-control " path="district"> --%>
								<%-- 										<form:option value="" label="--Chọn quận--" /> --%>
								<%-- 										<form:options items="${districts}" /> --%>
								<%-- 									</form:select> --%>
								<!-- 								</div> -->
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="ward">Phường</label>
								<div class="col-sm-9">
									<input type="text" id="ward" class="form-control" name="ward"
										value="${buildingModel.ward}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="numberOfBasement">Đường</label>
								<div class="col-sm-9">
									<input type="text" id="street" class="form-control"
										name="street" value="${buildingModel.street}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="structure">Kết cấu </label>
								<div class="col-sm-9">
									<input type="text" id="structure" class="form-control"
										name="structure" value="${buildingModel.structure}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="rentArea">Số tầng hầm </label>
								<div class="col-sm-9">
									<input type="number" id="numberOfBasement" class="form-control"
										name="numberOfBasement"
										value="${buildingModel.numberOfBasement}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="floorArea">Diện tích sàn </label>
								<div class="col-sm-9">
									<input type="number" id="floorArea" class="form-control"
										name="floorArea" value="${buildingModel.floorArea}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="numberOfBasement">Hướng</label>
								<div class="col-sm-9">
									<input type="text" id="direction" class="form-control"
										name="direction" value="${buildingModel.direction}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="numberOfBasement">Hạng</label>
								<div class="col-sm-9">
									<input type="text" id="level" class="form-control" name="level"
										value="${buildingModel.level}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="rentArea">Diện tích thuê </label>
								<div class="col-sm-9">
									<input type="text" id="rentArea" class="form-control"
										name="rentArea" value="${buildingModel.rentArea}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="rentPrice">Giá thuê </label>
								<div class="col-sm-9">
									<input type="number" id="rentPrice" class="form-control"
										name="rentPrice" value="${buildingModel.rentPrice}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="rentPriceDescription">Mô tả giá</label>
								<div class="col-sm-9">
									<input type="text" id="rentPriceDescription"
										class="form-control" name="rentPriceDescription"
										value="${buildingModel.rentPriceDescription}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="serviceFee">Phí dịch vụ</label>
								<div class="col-sm-9">
									<input type="text" id="serviceFee" class="form-control"
										name="serviceFee" value="${buildingModel.serviceFee}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="carFee">Phí ô tô</label>
								<div class="col-sm-9">
									<input type="text" id="carFee" class="form-control"
										name="carFee" value="${buildingModel.carFee}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="motoFee">Phí mô tô</label>
								<div class="col-sm-9">
									<input type="text" id="motoFee" class="form-control"
										name="motoFee" value="${buildingModel.motoFee}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="overtimefee">Phí ngoài giờ</label>
								<div class="col-sm-9">
									<input type="text" id="overtimeFee" class="form-control"
										name="overtimeFee" value="${buildingModel.overtimeFee}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="waterFee">Phí nước</label>
								<div class="col-sm-9">
									<input type="text" id="waterFee" class="form-control"
										name="waterFee" value="${buildingModel.waterFee}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="electricityFee">Tiền điện</label>
								<div class="col-sm-9">
									<input type="text" id="electricityFee" class="form-control"
										name="electricityFee" value="${buildingModel.electricityFee}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="deposit">Đặt cọc</label>
								<div class="col-sm-9">
									<input type="text" id="deposit" class="form-control"
										name="deposit" value="${buildingModel.deposit}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="payment">Thanh toán</label>
								<div class="col-sm-9">
									<input type="text" id="payment" class="form-control"
										name="payment" value="${buildingModel.payment}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="rentTime">Thời hạn thuê</label>
								<div class="col-sm-9">
									<input type="text" id="rentTime" class="form-control"
										name="rentTime" value="${buildingModel.rentTime}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="decorationTime">Thời gian trang trí</label>
								<div class="col-sm-9">
									<input type="text" id="decorationTime" class="form-control"
										name="decorationTime" value="${buildingModel.decorationTime}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="managerName">Tên quản lí</label>
								<div class="col-sm-9">
									<input type="text" id="managerName" class="form-control"
										name="managerName" value="${buildingModel.managerName}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="managerPhone">SĐT quản lí</label>
								<div class="col-sm-9">
									<input type="text" id="managerPhone" class="form-control"
										name="managerPhone" value="${buildingModel.managerPhone}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="brokerageFee">Phí môi giới</label>
								<div class="col-sm-9">
									<input type="text" id="brokerageFee" class="form-control"
										name="brokerageFee" value="${buildingModel.brokerageFee}" />
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">
									Loại tòa nhà </label>
								<div class="col-sm-9">
									<c:forEach var="item" items="${buildingTypes}">
										<div class="checkbox-inline">
											<label> <input name="buildingTypes" id="${item.key}"
												value="${item.key}" type="checkbox" class="ace" /> <span
												class="lbl">${item.value}</span>
											</label>
										</div>
									</c:forEach>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="note">Ghi chú</label>
								<div class="col-sm-9">
									<input type="text" id="note" class="form-control" name="note"
										value="" />
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="types"></label>
								<div class="col-sm-9">
									<button type="button" class="btn btn-primary"
										id="btnAddBuilding">Thêm tòa nhà</button>
									<button type="button" class="btn btn-primary">Hủy</button>
								</div>
							</div>

							</form>
						</form:form>
					</div>

				</div>
				<!-- /.row -->

			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->

	<script>
		$('#district').val('${buildingModel.district}');
		<c:forEach var="item" items="${buildingModel.types}">
		$('#${item}')[0].checked = true;
		</c:forEach>
		console.log("c");
		 $('#btnAddBuilding').click(function(e) {
			e.preventDefault();
			//call api addBuilding
			var data = {}; // định dạng jason nhưng data là javaScript Object phải chuyển sang jason để sever nhận
			var buildingTypes = []; //mảng
			var formdata = $('#formEdit').serializeArray();
			$.each(formdata, function(index, v) {
				if (v.name == 'buildingTypes') {
					buildingTypes.push(v.value);
				} else {
					data["" + v.name + ""] = v.value;
				}

			});
			//data['numberOfBasement'] = 100; // 100 ở đây là value
			//data['rentArea'] = '100,200,300';
			//buildingTypes.push('tang-tret');
			//buildingTypes.push('nguyen-can');
			data['types'] = buildingTypes;
			data['id'] = "${buildingModel.id}";
			$.ajax({
				type : "POST",
				url : '${buildingAPI}', //thêm url api addBuilding
				data : JSON.stringify(data), // chuyển từ javaScript Object sang Json
				dataType : "json", // kiểu dữ liệu từ sever về client
				contentType : "application/json",// kiểu dữ liệu từ client về sever			
				success : function(response) {
					console.log('success');
					window.location.href = '${redirect}';
				},
				error : function(response) {
					console.log('failed');
					console.log(response);
				},
			});

		}); 
	</script>

</body>

</html>
