package com.elna.gallery.viewmodel;

import com.elna.gallery.model.Photo;
import java.util.List;

/**
 *
 *
 */

public interface IMainActivityView extends IView {

    void load(List<Photo> items);

}
