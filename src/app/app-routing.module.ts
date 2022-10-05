import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router"
import { AddEmployeeComponent } from "./components/add-employee/add-employee.component";
import { EmployeeDetailsComponent } from "./components/employee-details/employee-details.component";
import { EmployeeListComponent } from "./components/employee-list/employee-list.component";
import { UpdateEmployeeComponent } from "./components/update-employee/update-employee.component";


const routes: Routes = [
    { path: '', redirectTo: '/employee-list', pathMatch: 'full' },
    { path: 'employee-list', component: EmployeeListComponent },
    { path: 'add-employee', component: AddEmployeeComponent },
    { path: 'update-employee/:id', component: UpdateEmployeeComponent },
    { path: 'employee-details/:id', component: EmployeeDetailsComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule {}
