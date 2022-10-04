<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="customerListURL" value="/admin/customer-list" />

<c:url var="loadStaffAPI" value="/api/building" />
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="assignmentBuildingAPI" value="/api/building/assignment"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%--<spring:message code="label.user.list"/>--%> Danh
	sách người dùng
</title>
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
						<div class="widget-box">
							<div class="widget-header">
								<h4 class="widget-title">Tim kiếm</h4>

								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="ace-icon fa fa-chevron-up"></i>
									</a>
								</div>
							</div>
							<div class="widget-body">
								<div class="widget-main">
									<form:form modelAttribute="modelSearch"
										action="${customerListURL}" id="listForm" method="GET">
										<div class="form-horizontal">
										
											<div class="form-group">
												<!-- PAGE CONTENT BEGINS -->

												<div class=" col-sm-6">
													<div>
														<label for="fullName">Tên </label>
														<form:input  path="fullName" cssClass="form-control"/>

													</div>
												</div>
												<div class=" col-sm-6">
													<div>
														<label for="phone">Di Động</label> 
														<form:input  path="phone" cssClass="form-control"/>
													</div>
												</div>
											</div>
											
											<div class="form-group">
												<!-- PAGE CONTENT BEGINS -->

												<div class=" col-sm-6">
													<div>
														<label for="email">Email</label>
														<form:input  path="email" cssClass="form-control"/>

													</div>
												</div>
												<div class=" col-sm-6">
													<div>
														<label for="staffId">Chọn nhân viên phụ trách</label>
														<form:select cssClass="form-control" path="staffId">
															<form:option value="" label="--Chọn nhân viên--" />
															<form:options items="${staffmaps}" />
														</form:select>
													</div>
												</div>
											</div>
									

											<div class="form-group">
												<div class=" col-sm-4">
													<button type="button" class="btn btn-sm btn-success"
														id="btnSearch">
														Tìm kiếm <i
															class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
													</button>
												</div>
											</div>
											
										</div>
									</form:form>

								</div>

							</div>
						</div>
					</div>

				</div>
				<!-- /.row -->
				<br>
				<div class="row">
					<div class="col-xs-12">
						<div class="pull-right">
							<button class="btn btn-white btn-info btn-bold"
								data-toggle="tooltip" title="thêm tòa nhà" >								
								<a href="<c:url value='/admin/edit'></c:url>"><i class="fa fa-plus" aria-hidden="true"></i></a>
							</button>
							<button class="btn btn-white btn-warning btn-bold"
								data-toggle="tooltip" title="xóa tòa nhà" id="btnDeleteBuilding">
								<i class="fa fa-trash" aria-hidden="true"></i>
							</button>

						</div>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xs-12">
						<table id="buildingList"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th></th>
									<th>Tên</th>
									<th>Nhân viên quản lí</th>
									<th>Di động</th>
									<th>Email</th>
									<th>Nhu cầu</th>
									<th>Người nhập</th>
									<th>Ngày nhập</th>
									<th>Tình trạng</th>
									<th>Thao tác</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${customers}">
									<tr>
										<td><input type="checkbox" value="${item.id}" id="checkbox_1">
										</td>
<%-- 										<td>${item.name}</td> --%>
<%-- 										<td>${item.address}</td> --%>
<%-- 										<td>${item.managerName}</td> --%>
<%-- 										<td>${item.managerPhone}</td> --%>
<%-- 										<td>${item.floorArea}</td> --%>
<%-- 										<td>${item.areaEmpty}</td> --%>
<%-- 										<td>${item.rentPrice}</td> --%>
<%-- 										<td>${item.borkerageFee}</td> --%>
										<td>
<!-- 											<button class="btn btn-xs btn-info" data-toggle="tooltip" -->
<%-- 												title="Giao tòa nhà" onclick="assignmentBuilding(${item.id})"> --%>
<!-- 												<i class="fa fa-bars" aria-hidden="true"></i> -->
<!-- 											</button> -->
<!-- 											<button class="btn btn-xs btn-info" data-toggle="tooltip" -->
<%-- 												title="Cập nhật " onclick="updateBuilding(${item.id})"> --%>
<%-- 												<a href="<c:url value='/admin/edit?buildingid=${item.id}'></c:url>"><i class="fa fa-pencil-square" aria-hidden="true"></i></a>				 --%>
<!-- 											</button> -->
										</td>
									</tr>
								</c:forEach>
							</tbody>

						</table>
					</div>
				</div>
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->

	<div class="modal" id="assignmentBuildingModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Danh sách nhân viên</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<table class="table table-bordered" id="staffList">
						<thead>
							<tr>
								<th>Chọn nhân viên</th>
								<th>Tên nhân viên</th>
							</tr>
						</thead>
						<tbody>
							<!-- 						  <tr> -->
							<!-- 							<td><input type="checkbox" value="2" id="checkbox_2" checked></td> -->
							<!-- 							<td>Nguyễn Văn B</td> -->
							<!-- 						  </tr> -->
							<!-- 						  <tr> -->
							<!-- 							<td><input type="checkbox" value="3" id="checkbox_3"></td> -->
							<!-- 							<td>Nguyễn Văn C</td> -->
							<!-- 						  </tr> -->
							<!-- 						  <tr> -->
							<!-- 							<td><input type="checkbox" value="4" id="checkbox_4"></td> -->
							<!-- 							<td>Nguyễn Văn D</td> -->
							<!-- 						  </tr> -->
						</tbody>
					</table>
					<input type="hidden" id="buildingId" name="buildingID" value="">
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						id="btnAssignBuilding">Giao tòa nhà</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Đóng</button>
				</div>

			</div>
		</div>
	</div>

	<script>
		//chức năng assignment
		let staffOld = [];
		function assignmentBuilding(buildingId) {
			openModalAssignmentBuilding();
			loadStaff(buildingId);
			$('#buildingId').val(buildingId);
			//console.log($('#buildingId').val());
		}
	

		function loadStaff(buildingId) {
			$
					.ajax({
						type : "GET",
						url : "${loadStaffAPI}/"+buildingId+"/staffs", //đang gắn cứng cần load building id tự động
						//data: JSON.stringify(data), // chuyển từ javaScript Object sang Json
						dataType : "json", // kiểu dữ liệu từ sever về client
						//contentType: "application/json",// kiểu dữ liệu từ client về sever			
						success : function(response) {
							//console.log("success");
							var row = '';
							staffOld = [];
							$
									.each(
											response.data,
											function(index, item) {
												row += '<tr>';
												row += '<td><input type="checkbox" value='+item.staffId+' id="checkbox '+item.staffId+'" +class="check-box-element" '+item.checked+'></td>';
												row += '<td>' + item.fullName
														+ '</td>';
												row += '</tr>';
												if(item.checked== 'checked'){
													staffOld.push(item.staffId)												}
											});
							$('#staffList tbody').html(row);
						},
						error : function(response) {
							console.log("failed");
							console.log(response);
						},
					});
		}
		//hiển thị modal
		function openModalAssignmentBuilding() {
			$('#assignmentBuildingModal').modal();
		}

		$('#btnAssignBuilding').click(
				function(e) {
					e.preventDefault();
					var data = {};
					var staffs = [];
					data['buildingId'] = $('#buildingId').val();
					//$('#staffList').find('tbody input[type = checkbox]');
					var staffs = $('#staffList').find(
							'tbody input[type = checkbox]:checked').map(
							function() {
								return $(this).val();
							}).get();
					
					data['staffIdsNew'] = staffs;
					data['staffIdsOld'] = staffOld
					//call api
					assignStaff(data);
				});

		function assignStaff(data) {
			$.ajax({
				type : "POST",
				url : "${assignmentBuildingAPI}", //
				data : JSON.stringify(data), // chuyển từ javaScript Object sang Json
				dataType : "json", // kiểu dữ liệu từ sever về client
				contentType : "application/json",// kiểu dữ liệu từ client về sever			
				success : function(response) {
					console.log("success");
				},
				error : function(response) {
					console.log("failed");
					console.log(response);
				},
			});
		}

		$('#btnDeleteBuilding').click(
				function(e) {
					e.preventDefault();
					var data = {};
					var buildingIds = $('#buildingList').find(
							'tbody input[type = checkbox]:checked').map(
							function() {
								return $(this).val();
							}).get();
					data['buildingIds'] = buildingIds;
					deleteBuilding(data);
				});

		function deleteBuilding(data) {
			$.ajax({
				type : "DELETE",
				url : '${buildingAPI}', //thêm url api deleteBuilding
				data : JSON.stringify(data), // chuyển từ javaScript Object sang Json
				dataType : "json", // kiểu dữ liệu từ sever về client
				contentType : "application/json",// kiểu dữ liệu từ client về sever			
				success : function(response) {
					console.log('success');
				},
				error : function(response) {
					console.log('failed');
					console.log(response);
				},
			});
		}
		

		$('#btnSearch').click(function(e) {
			e.preventDefault();
			$('#listForm').submit();
		});
	</script>
</body>

</html>