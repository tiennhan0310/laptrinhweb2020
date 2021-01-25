<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container-scroller">
<fmt:setLocale value="${locale }" />

	<fmt:setBundle basename="messages" />
		<!-- partial:partials/_navbar.jsp -->
		<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<div class="navbar-brand-wrapper d-flex justify-content-center">
				<div
					class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
					<a class="navbar-brand brand-logo" href="admin-trang-chu"><img
						src="img/logo.png" alt="logo" /></a>
					<button class="navbar-toggler navbar-toggler align-self-center"
						type="button" data-toggle="minimize">
						<span class="mdi mdi-sort-variant"></span>
					</button>
				</div>
			</div>
			<div
				class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
				<ul class="navbar-nav mr-lg-4 w-100">
						<div class="logo" style="">
					<form action="search" method="post" height="25"
						style="padding: -16px 0px;">
						<a href="${english }" title="English"> <img
							src="img/en.png" height="25" style="padding: 0px 0px">
						</a> <a href="${vietnam }" title="Vietnamese"> <img
							src="img/vi.png" height="25" style="padding: 0px 0px">
						</a> 
					</form>
				</div>
				</ul>
				<ul class="navbar-nav navbar-nav-right">
								<li class="nav-item nav-profile dropdown"><a
						class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"
						id="profileDropdown"> <img src="img/logo.png" alt="profile" style="width: 50%"/> <span
							class="nav-profile-name"> Admin</span>
					</a>
						<div class="dropdown-menu dropdown-menu-right navbar-dropdown"
							aria-labelledby="profileDropdown">
							<a class="dropdown-item"> <i
								class="mdi mdi-settings text-primary"></i> <fmt:message key="caiDatTaiKhoan"/>
							</a> <a class="dropdown-item" href = "${pageContext.request.contextPath }/dang-xuat"> <i
								class="mdi mdi-logout text-primary"></i> <fmt:message key="dangXuat"/>
							</a>
						</div></li>
				</ul>
				<button
					class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
					type="button" data-toggle="offcanvas">
					<span class="mdi mdi-menu"></span>
				</button>
			</div>
		</nav>
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:partials/_sidebar.jsp -->
			<nav class="sidebar sidebar-offcanvas" id="sidebar">
				<ul class="nav">
							<li class="nav-item"><a class="nav-link"
						data-toggle="collapse" href="#ui-basic" aria-expanded="false"
						aria-controls="ui-basic"> <i
							class="mdi mdi-circle-outline menu-icon"></i> <span
							class="menu-title"><fmt:message key="quanLyKho"/> </span> <i class="menu-arrow"></i>
					</a>
						<div class="collapse" id="ui-basic">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item"><a class="nav-link"
									href="admin-san-pham-da-ban"><fmt:message key="sanPhamDaBan"/></a></li>
								<li class="nav-item"><a class="nav-link"
									href="admin-san-pham-con-lai"><fmt:message key="sanPhamConLai"/></a></li>
							</ul>
						</div></li>
					<li class="nav-item"><a class="nav-link"
						href="admin-quan-ly-dien-thoai"> <i
							class="mdi mdi-view-headline menu-icon"></i> <span
							class="menu-title"><fmt:message key="quanLyDienThoai"/></span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="admin-quan-ly-don-hang"> <i
							class="mdi mdi-chart-pie menu-icon"></i> <span class="menu-title"><fmt:message key="quanLyDonHang"/></span>
					</a></li>
					
					<li class="nav-item"><a class="nav-link"
						href="admin-quan-ly-khach-hang"> <i
							class="mdi mdi-grid-large menu-icon"></i> <span
							class="menu-title"><fmt:message key="quanLyKhachHang"/></span>
					</a></li>


				</ul>
			</nav>
