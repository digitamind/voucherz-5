import Dashboard from "@material-ui/icons/Dashboard";
import LocationOn from "@material-ui/icons/LocationOn";
import DashboardPage from "../Views/Dashboard/DashboardPage";
import Voucherz from "../Views/Voucherz/Voucherz"
import CreateVouchers from "../Views/CreateVoucher/CreateVouchers";
import CreateVoucher from "../Views/CreateVoucher/CreateVoucher"
import SimpleTable from "../Views/SimpleTable/SimpleTable";
import ValueTable from "../Views/SimpleTable/ValueTable";

const dashboardRoutes = [
  {
    path: "/dashboard",
    sidebarName: "Dashboard",
    navbarName: "Dashboard",
    icon: Dashboard,
    component: DashboardPage
  },
  {
    path: "/voucherz",
    sidebarName: "Voucherz",
    navbarName: "Voucherz",
    icon: LocationOn,
    component: Voucherz
  },
  {
    path: "/Simpletable",
    sidebarName: "Discount Voucher",
    navbarName: "Discount Voucher",
    icon: LocationOn,
    component: SimpleTable
  },
  {
    path: "/valuetable",
    sidebarName: "Value Voucher",
    navbarName: "Value Voucher",
    icon: LocationOn,
    component: ValueTable
  },
  {
    path: "/gifttable",
    sidebarName: "Gift Voucher",
    navbarName: "Gift Voucher",
    icon: LocationOn,
    component: ValueTable
  },
  {
    path: "/CreateVoucherBulk",
    sidebarName: "",
    navbarName: "Bulk Voucher",
    icon: "",
    component: CreateVouchers
  },
  {
    path: "/CreateVoucherStandalone",
    sidebarName: "",
    navbarName: "Standalone Voucher",
    icon: "",
    component: CreateVoucher
  },
  { redirect: true, path: "/", to: "/dashboard", navbarName: "Redirect" }
];

export default dashboardRoutes;
