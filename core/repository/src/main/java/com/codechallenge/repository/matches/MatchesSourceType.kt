import com.codechallenge.model.Match
import com.codechallenge.repository.matches.MatchesRepository

internal interface MatchesSourceType {

    val runConcurrentWithNext: Boolean
    val isLastSource: Boolean
    val nextSource: MatchesSourceType

    suspend fun loadMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match>

}

internal class RunningMatches(private val matchesRepository: MatchesRepository) : MatchesSourceType {

    // TODO Spec doubt - If we can assume the running matches list size are never bigger than 10
    override val runConcurrentWithNext: Boolean = true
    override val isLastSource: Boolean = false
    override val nextSource: MatchesSourceType = UpcomingMatches(matchesRepository)

    override suspend fun loadMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match> =
        matchesRepository.getRunningMatches(pageSize, pageNumber, latestDate)
}

internal class UpcomingMatches(private val matchesRepository: MatchesRepository) : MatchesSourceType {

    override val runConcurrentWithNext: Boolean = false
    override val isLastSource: Boolean = false
    override val nextSource: MatchesSourceType = PastMatches(matchesRepository)

    override suspend fun loadMatches(pageSize: Int, pageNumber: Int, latestDate: String) =
        matchesRepository.getUpcomingMatches(pageSize, pageNumber, latestDate)
}

internal class PastMatches(private val matchesRepository: MatchesRepository) : MatchesSourceType {

    override val runConcurrentWithNext: Boolean = false
    override val isLastSource: Boolean = true
    override val nextSource: MatchesSourceType = this

    override suspend fun loadMatches(pageSize: Int, pageNumber: Int, latestDate: String) =
        matchesRepository.getPastMatches(pageSize, pageNumber, latestDate)
}