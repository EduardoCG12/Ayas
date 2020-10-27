package com.dosdeemetres.ayashome.Fragments.Adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dosdeemetres.ayashome.Clases.Reserva;
import com.dosdeemetres.ayashome.R;

import java.util.List;

/*  creamos el adapter para manejar el recyclerView de reservas
    hereda de RecyclerView.Adapter y de la clase interna ReservaBasicViewHolder (que tenemos que crear)
 */
public class MyReservaRecyclerViewAdapter extends RecyclerView.Adapter<MyReservaRecyclerViewAdapter.ViewHolder> {
    // propiedades
    private final List<Reserva> mValues;
    // para diferenciar items pares e impares
    private static final int PAR = 0;
    private static final int IMPAR = 1;

    public MyReservaRecyclerViewAdapter(List<Reserva> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_lista_reserva_item_impar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getHora());
        holder.mContentView.setText(mValues.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Reserva mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.tvHora);
            mContentView = (TextView) view.findViewById(R.id.tvFecha);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}