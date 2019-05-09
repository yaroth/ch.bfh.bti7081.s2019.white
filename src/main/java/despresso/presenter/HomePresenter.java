package despresso.presenter;

import despresso.view.HomeViewImpl;

public class HomePresenter  {

    private MainPresenter mainPresenter;
    private HomeViewImpl view;

    public HomePresenter(MainPresenter mainPresenter, HomeViewImpl view) {
        this.mainPresenter = mainPresenter;
        this.view = view;
    }


}
