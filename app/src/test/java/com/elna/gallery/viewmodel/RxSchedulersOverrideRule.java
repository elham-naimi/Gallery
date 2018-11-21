package com.elna.gallery.viewmodel;


import android.support.annotation.NonNull;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/*  Copyright 2015 Ribot Ltd.

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
        

        FILE CHANGED 2016 by Patrick Löwenstein */

public class RxSchedulersOverrideRule implements TestRule {
    private Scheduler immediate = new Scheduler() {
        @Override
        public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
            // this prevents StackOverflowErrors when scheduling with a delay
            return super.scheduleDirect(run, 0, unit);
        }

        @Override
        public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(Runnable::run);
        }
    };

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
                RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
                RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
                RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);

                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}