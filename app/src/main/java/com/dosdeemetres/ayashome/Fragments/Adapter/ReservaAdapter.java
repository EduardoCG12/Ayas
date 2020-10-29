package com.dosdeemetres.ayashome.Fragments.Adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dosdeemetres.ayashome.Clases.OnReservaInteractionListener;
import com.dosdeemetres.ayashome.Clases.Reserva;
import com.dosdeemetres.ayashome.R;

import java.util.List;

/*  creamos el adapter para manejar el recyclerView de reservas
    hereda de RecyclerView.Adapter y de la clase interna ReservaBasicViewHolder (que tenemos que crear)
 */
public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ViewHolder> {
    // propiedades
    private final List<Reserva> mValues;
    private final OnReservaInteractionListener mListener;
    // para diferenciar items pares e impares
    private static final int PAR = 0;
    private static final int IMPAR = 1;

    public ReservaAdapter(List<Reserva> items, OnReservaInteractionListener reservaListener) {
        mValues = items;
        mListener = reservaListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_reserva_item_impar, parent, false);

        if (viewType == PAR) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_reserva_item_impar, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_reserva_item_par, parent, false);
        }


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvHora.setText(mValues.get(position).getHora());
        holder.tvFecha.setText(mValues.get(position).getFecha());
        holder.tvUsuario.setText(mValues.get(position).getUsuario());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mListener.OnReservaClick(holder.mItem);
            }
        });

        holder.ivEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onReservaEliminada(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvHora;
        public final TextView tvFecha;
        public final TextView tvUsuario;
        public final ImageView ivEliminar;
        public Reserva mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            tvHora = (TextView) view.findViewById(R.id.tvHora);
            tvFecha = (TextView) view.findViewById(R.id.tvFecha);
            tvUsuario = (TextView) view.findViewById(R.id.tvUsuario);
            ivEliminar = (ImageView) view.findViewById(R.id.ivEliminar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvFecha.getText() + "'";
        }
    }

    // Diferenciar entre elemento par e impar
    @Override
    public int getItemViewType (int position){

        if (position%2==0){
            return PAR;
        }else {
            return IMPAR;
        }
    }
}