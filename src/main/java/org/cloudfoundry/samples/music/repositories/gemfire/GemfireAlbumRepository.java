package org.cloudfoundry.samples.music.repositories.gemfire;

import org.cloudfoundry.samples.music.domain.Album;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("gemfire")
public interface GemfireAlbumRepository extends GemfireRepository<Album, String> {
}