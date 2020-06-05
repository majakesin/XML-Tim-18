import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { NavigationComponent } from './navigation/navigation.component';
import { LoginComponent } from './login/login.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { CreateAdvertComponent } from './advert/create-advert/create-advert.component';
import { MyAdvertsComponent } from './advert/my-adverts/my-adverts.component';
import { AllAdvertsComponent } from './advert/all-adverts/all-adverts.component';
import { RegisterCarComponent } from './advert/car/register-car/register-car.component';
import { DefinePriceComponent } from './advert/price/define-price/define-price.component';
import { MyPriceListComponent } from './advert/price/my-price-list/my-price-list.component';
import { MyCarsComponent } from './advert/car/my-cars/my-cars.component';
import { CarBrandComponent } from './car-brand/car-brand.component';
import { CarClassComponent } from './car-class/car-class.component';
import { CarFuelTypeComponent } from './car-fuel-type/car-fuel-type.component';
import { CarModelComponent } from './car-model/car-model.component';
import { CarTranssmisionTypeComponent } from './car-transsmision-type/car-transsmision-type.component';
import { NavigationAdminComponent } from './navigation-admin/navigation-admin.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateCarBrandComponent } from './car-brand/create-car-brand/create-car-brand.component';
import {MatDialogModule} from "@angular/material/dialog";
import { CreateCarClassComponent } from './car-class/create-car-class/create-car-class.component';
import { CreateFuelTypeComponent } from './car-fuel-type/create-fuel-type/create-fuel-type.component';
import { CreateCarModelComponent } from './car-model/create-car-model/create-car-model.component';
import { CreateTranssmisionTypeComponent } from './car-transsmision-type/create-transsmision-type/create-transsmision-type.component';
import { UpdateCarBrandComponent } from './car-brand/update-car-brand/update-car-brand.component';
import { UpdateCarClassComponent } from './car-class/update-car-class/update-car-class.component';
import { UpdateFuelTypeComponent } from './car-fuel-type/update-fuel-type/update-fuel-type.component';
import { UpdateCarModelComponent } from './car-model/update-car-model/update-car-model.component';
import { UpdateCarTransmissionComponent } from './car-transsmision-type/update-car-transmission/update-car-transmission.component';





@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    NavigationComponent,
    LoginComponent,
    CreateAdvertComponent,
    MyAdvertsComponent,
    AllAdvertsComponent,
    RegisterCarComponent,
    DefinePriceComponent,
    MyPriceListComponent,
    MyCarsComponent,
    CarBrandComponent,
    CarClassComponent,
    CarFuelTypeComponent,
    CarModelComponent,
    CarTranssmisionTypeComponent,
    NavigationAdminComponent,
    CreateCarBrandComponent,
    CreateCarClassComponent,
    CreateFuelTypeComponent,
    CreateCarModelComponent,
    CreateTranssmisionTypeComponent,
    UpdateCarBrandComponent,
    UpdateCarClassComponent,
    UpdateFuelTypeComponent,
    UpdateCarModelComponent,
    UpdateCarTransmissionComponent,


  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatPaginatorModule,
        MatSortModule,
        MatTableModule,
        MatIconModule,
        MatPaginatorModule,
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
        MatDialogModule,

    ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
