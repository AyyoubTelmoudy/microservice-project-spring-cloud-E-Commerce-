import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomersComponent } from './customers/customers.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { OrdersComponent } from './orders/orders.component';
import { ProductComponent } from './product/product.component';
import { ChartComponent } from './chart/chart.component';

const routes: Routes = [
  {path:"products",component:ProductComponent},
  {path:"customers",component:CustomersComponent},
  {path:"orders/:customerId",component:OrdersComponent},
  {path:"order-details/:orderId",component:OrderDetailsComponent},
  {path:"dashboard",component:ChartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
