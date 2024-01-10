import { OrderResponse } from "src/app/order/types/OrderResponse.interface";

export interface UserResponse{
    id: string,
    name: string,
    email: string,
    listOrders: OrderResponse[]
}