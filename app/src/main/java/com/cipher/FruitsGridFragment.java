package com.cipher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo Gorio on 26/06/2015.
 */
public class FruitsGridFragment extends InternetFragment implements
        Response.Listener<JSONObject>, Response.ErrorListener {

    List<Fruit> mFruits;
    GridView mGridView;
    TextView mTextMensagem;
    ArrayAdapter<Fruit> mAdapter;
    ProgressBar mProgressBar;

    boolean mIsRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_fruits_grid, null);
        mProgressBar = (ProgressBar) layout.findViewById(R.id.progressBar);
        mTextMensagem = (TextView) layout.findViewById(android.R.id.empty);
        mGridView = (GridView) layout.findViewById(R.id.gridview);
        mGridView.setEmptyView(mTextMensagem);
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mFruits == null) {
            mFruits = new ArrayList<Fruit>();
        }
        mAdapter = new FruitsGridAdapter(getActivity(), mFruits);
        mGridView.setAdapter(mAdapter);

        if (!mIsRunning) {
            if (FruitsHttp.temConexao(getActivity())) {
                iniciarDownload();
            } else {
                mTextMensagem.setText("Sem conexão");
            }
        } else {
            exibirProgress(true);
        }
    }

    private void exibirProgress(boolean exibir) {
        if (exibir) {
            mTextMensagem.setText("Baixando informações dos Fruits...");
        }
        mTextMensagem.setVisibility(exibir ? View.VISIBLE : View.GONE);
        mProgressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    public void iniciarDownload() {
        mIsRunning = true;
        exibirProgress(true);
        RequestQueue queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        JsonObjectRequest request =
                new JsonObjectRequest(
                        Request.Method.GET,    // Requisição via HTTP_GET
                        FruitsHttp.Fruits_URL_JSON,  // url da requisição
                        null,  // JSONObject a ser enviado via POST
                        this,  // Response.Listener
                        this); // Response.ErrorListener
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        mIsRunning = false;
        exibirProgress(false);
        mTextMensagem.setText("Falha ao obter Fruits");
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        mIsRunning = false;
        exibirProgress(false);
        try {
            List<Fruit> Fruits = FruitsHttp.lerJsonFruits(jsonObject);
            mFruits.clear();
            mFruits.addAll(Fruits);
            mAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

