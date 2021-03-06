/*
 * Copyright 2012-2013 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.server.inspector.view;

import io.selendroid.ServerInstrumentation;
import io.selendroid.server.inspector.BaseInspectorViewRenderer;
import io.selendroid.server.inspector.InspectorServlet;
import io.selendroid.server.inspector.SelendroidInspectorView;
import io.selendroid.server.model.SelendroidDriver;

import java.nio.charset.Charset;

import org.json.JSONException;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

public class InspectorView extends SelendroidInspectorView {
  public InspectorView(ServerInstrumentation serverInstrumentation, SelendroidDriver driver) {
    super(serverInstrumentation, driver);
  }

  @Override
  public void render(HttpRequest request, HttpResponse response) throws JSONException {
    response.header("Content-Type", "text/html").charset(Charset.forName("UTF-8")).status(200)
        .content(new MyInspectorViewRenderer().buildHtml()).end();
  }

  public class MyInspectorViewRenderer extends BaseInspectorViewRenderer {
    public String getResource(String name) {
      return InspectorServlet.INSPECTOR_RESSOURCE + "/" + name;
    }

    public String getScreen() {
      return getResource(ResourceView.SCREENSHOT);
    }
  }
}
