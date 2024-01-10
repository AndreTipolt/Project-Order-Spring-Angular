import { OrderItemResponse } from "./OrderItemResponse.interface";

export interface OrderResponse{
    id: string,
    moment: string, // Date
    listOrderItems: OrderItemResponse[]
    status: string,
    payment: string,
    total: number
}