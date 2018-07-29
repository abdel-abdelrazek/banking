<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div class="page-sidebar-wrapper">
	<!-- END SIDEBAR -->
	<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
	<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
	<div class="page-sidebar navbar-collapse collapse">
		<!-- BEGIN SIDEBAR MENU -->
		<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
		<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
		<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
		<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<ul
			class="page-sidebar-menu  page-header-fixed page-sidebar-menu-hover-submenu "
			data-keep-expanded="false" data-auto-scroll="true"
			data-slide-speed="200">
			<li class="nav-item start active open"><a href="<%=path%>/index"
				class="nav-link nav-toggle"> <i class="icon-home"></i> <span
					class="title">Home</span> <span class="selected"></span> <span
					class="arrow open"></span>
			</a>
				<ul class="sub-menu">

				</ul></li>
			<li class="nav-item  "><a href="<%=path%>/customer/index"
				class="nav-link nav-toggle"> <i class="icon-diamond"></i> <span
					class="title">Customers</span> <span class="arrow"></span>
			</a>
				<ul class="sub-menu">

				</ul></li>

			<li class="nav-item  "><a href="<%=path%>/exchange/exchange"
				class="nav-link nav-toggle"> <i class="icon-pointer"></i> <span
					class="title">Exchange Money</span> <span class="arrow"></span>
			</a>
				<ul class="sub-menu">

				</ul></li>

			<security:authorize access="isAnonymous()">
				<li class="nav-item  "><a href="/cbs/login" target="_blank"
					class="nav-link nav-toggle"> <i class="icon-layers"></i> <span
						class="title">login</span> <span class="arrow"></span>
				</a></li>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				<li class="nav-item  "><a href="/cbs/login" target="_blank"
					class="nav-link nav-toggle"> <i class="icon-layers"></i> <span
						class="title">logout</span> <span class="arrow"></span>
				</a></li>
			</security:authorize>

		</ul>
		<!-- END SIDEBAR MENU -->
	</div>
	<!-- END SIDEBAR -->
</div>