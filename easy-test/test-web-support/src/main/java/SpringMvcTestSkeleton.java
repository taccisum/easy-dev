import cn.tac.framework.easydev.test.common.TestSkeleton;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author tac
 * @since 2.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class SpringMvcTestSkeleton extends TestSkeleton {
    protected MockMvc mvc;

//    @Autowired
    private WebApplicationContext webApplicationContext;
//    @Autowired
//    private ObjectMapper objectMapper;

    @Before
    public void __setUp__() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
