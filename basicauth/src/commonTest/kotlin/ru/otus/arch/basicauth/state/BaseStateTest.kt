package ru.otus.arch.basicauth.state

import com.motorro.commonstatemachine.CommonMachineState
import com.motorro.commonstatemachine.CommonStateMachine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.kodein.mock.Mock
import org.kodein.mock.UsesMocks
import org.kodein.mock.generated.injectMocks
import org.kodein.mock.tests.TestsWithMocks
import ru.otus.arch.basicauth.api.BasicAuthFlowHost
import ru.otus.arch.basicauth.data.BasicAuthGesture
import ru.otus.arch.basicauth.data.BasicAuthUiState
import ru.otus.arch.domain.session.SessionManager
import ru.otus.arch.domain.session.data.Session
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
@UsesMocks(
    BasicStateFactory::class,
    CommonStateMachine::class,
    SessionManager::class,
    BasicAuthFlowHost::class
)
internal abstract class BaseStateTest : TestsWithMocks() {
    override fun setUpMocks() {
        mocker.injectMocks(this)
    }

    @Mock
    lateinit var stateFactory: BasicStateFactory
    @Mock
    lateinit var stateMachine: CommonStateMachine<BasicAuthGesture, BasicAuthUiState>
    @Mock
    lateinit var flowHost: BasicAuthFlowHost

    // MockMp injects unpredictable when working with base classes
    // So all mocks initialized here
    @Mock
    lateinit var sessionManager: SessionManager

    protected lateinit var context: BasicAuthContext
    protected lateinit var nextState: CommonMachineState<BasicAuthGesture, BasicAuthUiState>
    protected lateinit var dispatcher: TestDispatcher

    @BeforeTest
    fun init() {
        every { stateMachine.setUiState(isAny()) } returns Unit
        every { stateMachine.setMachineState(isAny()) } returns Unit

        every { sessionManager.session } returns MutableStateFlow(Session.NONE).asStateFlow()

        every { flowHost.onLogin() } returns Unit
        every { flowHost.onCancel() } returns Unit

        context = object : BasicAuthContext {
            override val factory: BasicStateFactory = stateFactory
            override val flowHost: BasicAuthFlowHost = this@BaseStateTest.flowHost
        }

        nextState = object : CommonMachineState<BasicAuthGesture, BasicAuthUiState>() { }
        dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)

        doInit()
    }

    protected fun doInit() = Unit

    @AfterTest
    fun deinit() {
        Dispatchers.resetMain()
    }

    protected fun test(testBody: suspend TestScope.() -> Unit) = runTest(dispatcher) {
        testBody()
    }
}