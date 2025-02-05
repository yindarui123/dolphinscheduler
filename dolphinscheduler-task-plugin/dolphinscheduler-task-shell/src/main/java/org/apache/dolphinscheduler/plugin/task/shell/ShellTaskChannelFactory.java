/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.plugin.task.shell;

import org.apache.dolphinscheduler.spi.params.base.ParamsOptions;
import org.apache.dolphinscheduler.spi.params.base.PluginParams;
import org.apache.dolphinscheduler.spi.params.base.Validate;
import org.apache.dolphinscheduler.spi.params.input.InputParam;
import org.apache.dolphinscheduler.spi.params.radio.RadioParam;
import org.apache.dolphinscheduler.spi.task.TaskChannel;
import org.apache.dolphinscheduler.spi.task.TaskChannelFactory;

import java.util.ArrayList;
import java.util.List;

public class ShellTaskChannelFactory implements TaskChannelFactory {
    @Override
    public TaskChannel create() {
        return new ShellTaskChannel();
    }

    @Override
    public String getName() {
        return "SHELL";
    }

    @Override
    public List<PluginParams> getParams() {
        List<PluginParams> paramsList = new ArrayList<>();

        InputParam nodeName = InputParam.newBuilder("name", "$t('Node name')")
                .addValidate(Validate.newBuilder()
                        .setRequired(true)
                        .build())
                .build();

        RadioParam runFlag = RadioParam.newBuilder("runFlag", "RUN_FLAG")
                .addParamsOptions(new ParamsOptions("NORMAL", "NORMAL", false))
                .addParamsOptions(new ParamsOptions("FORBIDDEN", "FORBIDDEN", false))
                .build();

        paramsList.add(nodeName);
        paramsList.add(runFlag);
        return paramsList;
    }
}
