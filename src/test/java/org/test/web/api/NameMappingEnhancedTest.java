package org.test.web.api;

import com.myapp.something.nice.ExcludeMeService;
import com.myapp.something.nice.NiceService;
import com.myapp.something.controller.NiceController;
import org.avaje.metric.MetricManager;
import org.avaje.metric.MockBucketTimedMetric;
import org.junit.Assert;
import org.junit.Test;

public class NameMappingEnhancedTest extends BaseTest {

  @Test
  public void testSuccessExecution() throws InterruptedException {

    NiceService niceService = new NiceService();
    
    MockBucketTimedMetric metric = MetricManager.testGetBucketTimedMetric("com.myapp.something.nice.NiceService.doNice");
    metric.testReset();
    MetricManager.testReset();
    
    Assert.assertEquals(0, metric.testGetCount());

    niceService.doNice();
    Assert.assertEquals(1, metric.testGetCount());
    Assert.assertTrue(MetricManager.testLastMetricOpcodeSuccess());

    niceService.doNice();
    Assert.assertEquals(2, metric.testGetCount());
    Assert.assertTrue(MetricManager.testLastMetricOpcodeSuccess());

    niceService.doNice();
    Assert.assertEquals(3, metric.testGetCount());
    Assert.assertTrue(MetricManager.testLastMetricOpcodeSuccess());
    
  }


  @Test
  public void testExclude() {

    ExcludeMeService excludeMeService = new ExcludeMeService();

    excludeMeService.excludeMe();
    excludeMeService.excludeMe();
    excludeMeService.excludeMe();
    excludeMeService.excludeMe();

  }

  @Test
  public void testController() {

    NiceController controller = new NiceController();

    controller.doStuff();
    controller.doStuff();
    controller.doStuff();
    controller.doStuff();
  }
}