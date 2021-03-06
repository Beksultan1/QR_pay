package com.beksultan.qr_pay.ui.business.director.add;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.model.business.director.addDepartment.SearchHeadResponse;
import com.beksultan.qr_pay.model.business.director.param.CreateParam;
import com.beksultan.qr_pay.model.business.director.param.HeadParam;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AddDepartmentPresenter extends MvpPresenter<AddDepartmentView> {

    public void getHead(HeadParam param) {
        App.getInstance().getApiService().getHead(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().onShowProgressBar())
                .doAfterTerminate(() -> getViewState().onHideProgressBar())
                .subscribe(new SingleObserver<SearchHeadResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(SearchHeadResponse searchHeadResponse) {
                        getViewState().onSetUpRecyclerView(searchHeadResponse.headList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });
    }

    public void createDepartment(CreateParam param) {
        App.getInstance().getApiService().createDepartment(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().onShowProgressBar())
                .doAfterTerminate(() -> getViewState().onHideProgressBar())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(String s) {
                        getViewState().onCreatedDepartment(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });
    }
}
