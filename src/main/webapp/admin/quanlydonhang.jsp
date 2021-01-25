<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Quản lý đơn hàng</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="admin/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="admin/vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link rel="stylesheet"
	href="admin/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet" href="admin/css/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="logo.png" />
<link rel="stylesheet"
	href="admin/font-awesome-4.7.0/css/font-awesome.min.css">
</head>

<body>
	<fmt:setLocale value="${locale }" />

	<fmt:setBundle basename="messages" />
	<jsp:include page="header.jsp" />
	<!-- partial -->
	<div class="col-xs-12">
		<table id="datatable-buttons"
			class="table table-striped table-bordered dataTable no-footer"
			role="grid" aria-describedby="datatable-buttons_info">
			<thead>
				<tr role="row">
					<th tabindex="0" aria-controls="datatable-buttons" rowspan="1"
						colspan="1" aria-label="SST: activate to sort column ascending"
						style="width: 50px;"><fmt:message key="stt" /></th>
					<th tabindex="0" aria-controls="datatable-buttons" rowspan="1"
						colspan="1"
						aria-label="Tên sản phẩm: activate to sort column ascending"
						style="width: 200px;"><fmt:message key="tenDienThoai" /></th>
					<th tabindex="0" aria-controls="datatable-buttons" rowspan="1"
						colspan="1"
						aria-label="Hãnh sản xuất: activate to sort column ascending"
						style="width: 200px;"><fmt:message key="nhaSanXuat" /></th>
					<th tabindex="0" aria-controls="datatable-buttons" rowspan="1"
						colspan="1" aria-label="Giá: activate to sort column ascending"
						style="width: 150px;"><fmt:message key="giaCaAdmin" /></th>
					<th tabindex="0" aria-controls="datatable-buttons" rowspan="1"
						colspan="1"
						aria-label="Tên khách hàng: activate to sort column ascending"
						style="width: 200px;"><fmt:message key="tenKhachHang" /></th>
					<th tabindex="0" aria-controls="datatable-buttons" rowspan="1"
						colspan="1" aria-label="SDT: activate to sort column ascending"
						style="width: 300px;"><fmt:message key="sdt" /></th>
					<th tabindex="0" aria-controls="datatable-buttons" rowspan="1"
						colspan="1"
						aria-label="Địa chỉ: activate to sort column ascending"
						style="width: 200px;"><fmt:message key="diaChi" /></th>
					<th tabindex="0" aria-controls="datatable-buttons" rowspan="1"
						colspan="1"
						aria-label="Ngày tạo: activate to sort column ascending"
						style="width: 200px;"><fmt:message key="ngayMua" /></th>
					<th tabindex="0" aria-controls="datatable-buttons" rowspan="1"
						colspan="1"
						aria-label="Thao tác: activate to sort column ascending"
						style="width: 150px;"><fmt:message key="thaoTac" /></th>
				</tr>
			</thead>

			<tbody>

				<c:forEach begin="0" end="${fn:length(listPay)}" var="i">
					<c:choose>
						<c:when test="${listPay[i].id  == null}">
						
						</c:when>
						<c:otherwise>
							<tr role="row" class="odd">
								<td>${listPay[i].id}</td>

								<td>${listPay[i].listPhone[i].name}</td>
								<td>${listPay[i].listPhone[i].nhaSanXuat }</td>
								<td><fmt:formatNumber type="number" maxFractionDigits="0"
										value="${listPay[i].listPhone[i].price}" /></td>
								<td>${list[i].customer.name }</td>

								<td>${listPay[i].phone }</td>
								<td>${listPay[i].address }</td>
								<td>${listPay[i].dateCreate }</td>
								<c:url value="admin-quan-ly-don-hang" var="confirm">
									<c:param name="confirm" value="${listPay[i].id }" />
								</c:url>

								<c:url value="admin-quan-ly-don-hang" var="delete">
									<c:param name="delete" value="${listPay[i].id }" />
								</c:url>
								<!-- phần xóa  -->
								<td class="center"><a href="${confirm }">
										<button title="" type="button"
											class="btn btn-xs btn-danger btn-round text-center">
											<fmt:message key="xacNhan" />

										</button>
								</a> <a href="${delete }">
										<button title=""
											class="btn btn-xs btn-info btn-round text-center">
											<fmt:message key="huy" />
										</button>
								</a></td>
								<!-- kết thúc phần xóa  -->
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</tbody>
		</table>

	</div>

	<!-- plugins:js -->
	<script src="admin/vendors/base/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<script src="admin/vendors/chart.js/Chart.min.js"></script>
	<script src="admin/vendors/datatables.net/jquery.dataTables.js"></script>
	<script src="admin/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script src="admin/js/off-canvas.js"></script>
	<script src="admin/js/hoverable-collapse.js"></script>
	<script src="admin/js/template.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script src="admin/js/dashboard.js"></script>
	<script src="admin/js/data-table.js"></script>
	<script src="admin/js/jquery.dataTables.js"></script>
	<script src="admin/js/dataTables.bootstrap4.js"></script>
	<!-- End custom js for this page-->

</body>

</html>
