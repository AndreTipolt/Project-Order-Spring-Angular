import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from 'src/app/category/types/Category.interface';
import { Product } from 'src/app/products/types/Product.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private readonly baseAPIURL = environment.baseAPIURL

  constructor(private httpClient: HttpClient) { }

  getAllCategories(): Observable<Category[]>{

    const apiURL = `${this.baseAPIURL}/categories`;

    return this.httpClient.get<Category[]>(apiURL);
  }

  filterCategoryById(listCategory: Category[], categoryId: string): Product[]{

    for (let category of listCategory){
      if(category.id === Number(categoryId)){
        return category.listProducts; // The products receive the category data reference a category
      }
    }
    return [];
  }

  findProductsByCategoryId(categoryId: string): Observable<Category>{

    const apiURL = `${this.baseAPIURL}/categories/${categoryId}`;

    return this.httpClient.get<Category>(apiURL);
  }
}
