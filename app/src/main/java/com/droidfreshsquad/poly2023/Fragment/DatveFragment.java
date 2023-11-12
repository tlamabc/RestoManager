package com.droidfreshsquad.poly2023.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droidfreshsquad.poly2023.R;

import java.util.ArrayList;
import java.util.List;

public class DatveFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView textViewTotal;
    private Button buttonCheckout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datve, container, false);

//        // Ánh xạ các thành phần trong layout
//        recyclerView = view.findViewById(R.id.recyclerViewProducts);
//        textViewTotal = view.findViewById(R.id.);
//        buttonCheckout = view.findViewById(R.id.buttonCheckout);

        // Khởi tạo danh sách sản phẩm (Đây là ví dụ, bạn cần thay thế bằng danh sách thực tế)
        List<String> productList = new ArrayList<>();
        productList.add("Sản phẩm 1");
        productList.add("Sản phẩm 2");
        productList.add("Sản phẩm 3");

//        // Khởi tạo và cài đặt adapter cho RecyclerView
//        RecyclerView.Adapter adapter = new ProductAdapter(productList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);

        // Tính tổng tiền (Đây là ví dụ, bạn cần thay thế bằng tính toán thực tế)
        int total = calculateTotal(productList);
//        textViewTotal.setText("Tổng tiền: " + total + " VND");

        // Xử lý sự kiện khi nhấn nút thanh toán
//        buttonCheckout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Xử lý logic thanh toán
//            }
//        });

        return view;




























    }

    // Hàm tính tổng tiền (Đây là ví dụ, bạn cần thay thế bằng tính toán thực tế)
    private int calculateTotal(List<String> productList) {
        // Logic tính tổng tiền ở đây
        return productList.size() * 100000; // Giả sử giá của mỗi sản phẩm là 100,000 VND
    }

    // Adapter cho RecyclerView
    private static class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

        private List<String> productList;

        public ProductAdapter(List<String> productList) {
            this.productList = productList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textViewProduct.setText(productList.get(position));
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewProduct;

            public ViewHolder(View view) {
                super(view);
                textViewProduct = view.findViewById(android.R.id.text1);
            }
        }
    }
}
