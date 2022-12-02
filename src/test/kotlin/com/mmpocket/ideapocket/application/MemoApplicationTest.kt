package com.mmpocket.ideapocket.application
import com.mmpocket.ideapocket.domain.directory.DirectoryRepository
import com.mmpocket.ideapocket.domain.memo.MemoId
import com.mmpocket.ideapocket.domain.memo.MemoParam
import com.mmpocket.ideapocket.domain.memo.MemoRepository
import com.mmpocket.ideapocket.domain.service.SequenceGenerator
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class MemoApplicationTest {
    private val memoRepository = mockk<MemoRepository>()
    private val sequenceGenerator = mockk<SequenceGenerator>()
    private val directoryRepository = mockk<DirectoryRepository>()
    private val memoApplication = MemoApplication(repository = memoRepository, sequenceGenerator = sequenceGenerator, directoryRepository = directoryRepository)

    @Test
    fun createMemoTest() {
        every {
            sequenceGenerator.generate(any())
        } returns 1

        every {
            memoRepository.save(any())
        } returnsArgument (0)

        val testParam = MemoParam(
            title = "테스트 메모1",
            content = "메모 내용은 길이가 길지롱"
        )

        val result = memoApplication.createMemo(testParam, userId = "1")

        assertThat(result.id.value).isEqualTo(1)
        assertThat(result.title).isEqualTo(testParam.title)
    }

    @Test
    fun updateMemoTest() {
        every {
            sequenceGenerator.generate(any())
        } returns 1

        every {
            memoRepository.save(any())
        } returnsArgument (0)

        val testParam = MemoParam(
            title = "테스트 메모1",
            content = "메모 내용은 길이가 길지롱"
        )

        val result = memoApplication.createMemo(testParam, userId = "1")

        assertThat(result.id.value).isEqualTo(1)
        assertThat(result.title).isEqualTo(testParam.title)

        every {
            memoRepository.findById(MemoId(1))
        } returns Optional.of(result)


        val updateParam = MemoParam(
            title = "테스트 메모 - 수정",
            content = "메모 내용을 수정해보자"
        )

        val updateResult = memoApplication.updateMemo(result.id.value.toString(), updateParam)
        assertThat(updateResult.id.value).isEqualTo(1)
        assertThat(updateResult.title).isEqualTo(updateParam.title)
    }

    @Test
    fun deleteMemoTest() {
        every {
            sequenceGenerator.generate(any())
        } returns 1

        every {
            memoRepository.save(any())
        } returnsArgument (0)

        val testParam = MemoParam(
            title = "테스트 메모1",
            content = "메모 내용은 길이가 길지롱"
        )

        val result = memoApplication.createMemo(testParam, userId = "1")

        every {
            memoRepository.findById(MemoId(1))
        } returns Optional.of(result)

        assertThat(result.id.value).isEqualTo(1)
        assertThat(result.title).isEqualTo(testParam.title)

        val deletedResult = memoApplication.deleteMemo("1")
        assertThat(deletedResult).isTrue()
    }

}